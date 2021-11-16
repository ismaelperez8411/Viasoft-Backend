package com.viasoft.viasoft.services;

import java.util.List;

import com.viasoft.viasoft.repos.LogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.viasoft.viasoft.models.LogUrlAvailable;

@Service
public class LogService {

    @Autowired
    LogRepository logRepo;

    public LogUrlAvailable saveLogObj(LogUrlAvailable lgObj) {
        return logRepo.save(lgObj);
    }

    // devuelve el estado actual de los servicios
    public List<LogUrlAvailable> getAllLogs(String fecha) {

        return logRepo.findAll();
    }

}
