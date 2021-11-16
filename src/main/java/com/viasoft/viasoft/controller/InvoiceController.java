package com.viasoft.viasoft.controller;

import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Map;

//import java.sql.Timestamp;

import com.viasoft.viasoft.models.InvoiceAvailableView;
import com.viasoft.viasoft.models.InvoiceHistoryView;
import com.viasoft.viasoft.models.LogUrlAvailable;
import com.viasoft.viasoft.services.InvoiceAvailableService;
import com.viasoft.viasoft.services.InvoiceViewService;
import com.viasoft.viasoft.services.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/api")
@Tag(name = "Invoice service", description = "API para la gestión de la disponibilidad de los servcios por (provincia)")
public class InvoiceController {

    @Autowired
    private InvoiceViewService invViewService;

    @Autowired
    private InvoiceAvailableService invAvailableService;

    @Autowired
    private LogService logService;

    // ---------------GET METHODS--------------------------

    // devuelve el historico de los estados de los servicios
    @GetMapping(path = "/all")
    // ResponseEntity<List<InvoiceHistoryView>>
    // getAllRecords(@RequestParam(defaultValue = "0") Integer pageNo,
    ResponseEntity<Map<String, Object>> getAllRecords(@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "histId") String sortBy,
            @RequestParam(defaultValue = "") String fecha, @RequestParam(defaultValue = "1") String sortOrder) {

        Map<String, Object> response = invViewService.getAllRecords(pageNo, pageSize, sortBy, fecha, sortOrder);

        /// return new ResponseEntity<List<InvoiceHistoryView>>(list, HttpStatus.OK);
        try {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // return invViewService.getAllRecords();
    }

    // devuelve la disponibilidad de los servicios durante el tiempo
    // (tomando como calculo los estados, para los cuales se dieron valores [-1, 0,
    // 1, 2] )
    // fijarse en la tabla states para ver a que pertenecen
    @GetMapping(path = "/availability")
    List<InvoiceAvailableView> getAvailableRecords() {
        return invAvailableService.getAllRecords();
    }

    // devuelve el servicio con la menor disponibilidad durante el tiempo
    @GetMapping(path = "/availability/minus")
    InvoiceAvailableView getAvailableMinusRecord() {
        return invAvailableService.getAllRecords().get(0);
    }

    // devuelve el estado actual de los servicios
    @GetMapping(path = "/currents")
    List<InvoiceHistoryView> getCurrentStates(@RequestParam(defaultValue = "") String aut) {
        return invViewService.getCurrentStates(aut);
    }

    // devuelve todos los Datetime registrdas en db por el historico
    @GetMapping(path = "/allDatetime")
    List<String> getAllDatetime() {
        return invViewService.getAllDatetime();
    }

    // devuelve el historico de los estados de los servicios filtrando por
    // autorizador
    @GetMapping(path = "/search/byAuto")
    List<InvoiceHistoryView> getCurrentStatesFilter(@RequestParam(value = "q") String autorizador) {
        return invViewService.getStatesForAutorizador(autorizador);
    }

    // devuelve el historico de los estados de los servicios filtrando por
    // autorizador
    @GetMapping(path = "/search/byDatetime")
    List<InvoiceHistoryView> getCurrentStatesFilterTime(@RequestParam(value = "q") String time) {
        return invViewService.getStatesForTime(time);
    }

    // devuelve un listado de logs de disponibilidad del sistema
    @GetMapping(path = "/logs")
    List<LogUrlAvailable> getUrlAvailability(@RequestParam(defaultValue = "") String time){
        return logService.getAllLogs(time);
    }

    // ---------------POST METHODS--------------------------

    // devuelve la disponibilidad de los servicios durante el tiempo
    // (tomando como calculo los estados, para los cuales se dieron valores [-1, 0,
    // 1, 2] )
    // fijarse en la tabla states para ver a que pertenecen
    @PostMapping(path = "/availability")
    List<InvoiceAvailableView> postAvailableRecords() {
        return invAvailableService.getAllRecords();
    }

    // devuelve el servicio con la menor disponibilidad durante el tiempo
    @PostMapping(path = "/availability/minus")
    InvoiceAvailableView postAvailableMinusRecord() {
        return invAvailableService.getAllRecords().get(0);
    }

    // devuelve todos los Datetime registrdas en db por el historico
    @PostMapping(path = "/allDatetime")
    List<String> postAllDatetime() {
        return invViewService.getAllDatetime();
    }

    // devuelve el historico de los estados de los servicios filtrando por
    // autorizador
    @PostMapping(path = "/search/byAuto")
    List<InvoiceHistoryView> postCurrentStatesFilter(@RequestParam(value = "q") String autorizador) {
        return invViewService.getStatesForAutorizador(autorizador);
    }

    // devuelve el historico de los estados de los servicios filtrando por
    // autorizador
    @PostMapping(path = "/search/byDatetime")
    List<InvoiceHistoryView> postCurrentStatesFilterTime(@RequestParam(value = "q") String time) {
        return invViewService.getStatesForTime(time);
    }

    /*
     * @Autowired private InvoiceRepository servicesRepository;
     * 
     * @Autowired private InvoiceHistoryRepository invHistRepo;
     * 
     * @PostMapping(path = "/add") // Map ONLY POST Requests public @ResponseBody
     * String addNewService(
     * 
     * @RequestParam String autorizador,
     * 
     * @RequestParam Boolean autorizacion,
     * 
     * @RequestParam Boolean autorizacionDevolucion,
     * 
     * @RequestParam Boolean discapacidad,
     * 
     * @RequestParam Boolean consultaProtocolo,
     * 
     * @RequestParam Boolean estadoServicio,
     * 
     * @RequestParam String tiempoMedio,
     * 
     * @RequestParam Boolean consultaRegistro,
     * 
     * @RequestParam Boolean recepcionEventos ) { // @ResponseBody means the
     * returned String is the response, not a view name // @RequestParam means it is
     * a parameter from the GET or POST request
     * 
     * Invoice n = new Invoice(autorizador); servicesRepository.save(n); return
     * "Saved"; }
     * 
     * @GetMapping(path = "/all") public @ResponseBody Iterable<Invoice>
     * getAllServices() { // This returns a JSON or XML with the users return
     * servicesRepository.findAll(); }
     * 
     * @GetMapping(path = "/current") public @ResponseBody Iterable<InvoiceHistory>
     * getCurrentStates() { // This returns a JSON or XML with the users return
     * invHistRepo.currents(); }
     */

}
