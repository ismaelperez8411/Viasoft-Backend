package com.viasoft.viasoft;

import java.io.IOException;
import java.sql.Timestamp;

import com.viasoft.viasoft.models.InvoiceHistory;
import com.viasoft.viasoft.models.LogUrlAvailable;
import com.viasoft.viasoft.models.Invoice;
import com.viasoft.viasoft.services.InvoiceService;
import com.viasoft.viasoft.services.LogService;
import com.viasoft.viasoft.services.InvoiceHistoryService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication(scanBasePackages = { "com.viasoft.viasoft.models", "com.viasoft.viasoft.repos",
		"com.viasoft.viasoft.controller", "com.viasoft.viasoft.services" })
@Configuration
@EnableScheduling
public class ViasoftApplication implements CommandLineRunner {

	static String url = "http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx";

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	InvoiceHistoryService invoiceHistoryService;

	@Autowired
	LogService saveLog;

	public Integer getEstate(String val) {
		return val.contains("verde") == true ? 2 : (val.contains("amarela") ? 1 : (val.contains("vermelho") ? 0 : -1));
	}

	@Scheduled(fixedRate = 300000, initialDelay = 300000)
	private void parseHTML() throws IOException {

		Timestamp time = new Timestamp(System.currentTimeMillis());
		String msg = "";
		Boolean active = true;
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (Exception e) {
			System.out.println(e);
			msg = "Domain not available: [ "+e.getMessage()+" ]";
			active = false;
		}
		saveLog.saveLogObj(new LogUrlAvailable(time, msg, active));

		if (active) {

			Invoice nvs = new Invoice();
			InvoiceHistory servHist = new InvoiceHistory();

			Elements tableLines = doc.select("table.tabelaListagemDados>tbody>tr");
			for (Element row : tableLines) {
				Elements tdCells = row.select("td");
				if (tdCells != null && tdCells.first() != null) {
					String aut = tdCells.get(0).text();
					log("----( " + aut + " ) Add|Update ------");
					nvs = new Invoice(aut);
					try {
						nvs = invoiceService.saveService(nvs);
					} catch (Exception e) {
						nvs = invoiceService.findByAutorizador(aut);
					}

					servHist = new InvoiceHistory();
					servHist.setMainService(nvs);
					servHist.setAutorizacion(getEstate(tdCells.get(1).html()));
					servHist.setAutorizacionDevolucion(getEstate(tdCells.get(2).html()));
					servHist.setDiscapacidad(getEstate(tdCells.get(5).html()));
					servHist.setConsultaProtocolo(getEstate(tdCells.get(4).html()));
					servHist.setEstadoServicio(getEstate(tdCells.get(3).html()));
					servHist.setTiempoMedio(tdCells.get(6).html());
					servHist.setConsultaRegistro(getEstate(tdCells.get(7).html()));
					servHist.setRecepcionEventos(getEstate(tdCells.get(8).html()));
					servHist.setTimeget(time);

					try {
						invoiceHistoryService.saveServiceHistory(servHist);
					} catch (Exception e) {
						log("-----Error(on save InvoiceHistory for [" + aut + "]): " + e.getMessage() + "---------");
					}
				}
			}

		}
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ViasoftApplication.class, args);
	}

	private static void log(String msg) {
		System.out.println(msg);
	}

	@Override
	public void run(String... args) throws Exception {
		parseHTML();
	}

}
