package com.viasoft.viasoft.repos;

import java.sql.Timestamp;
import java.util.List;

import com.viasoft.viasoft.models.LogUrlAvailable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogUrlAvailable,Timestamp> {
    

    public List<LogUrlAvailable> findByDatetime(Timestamp datetime);

}
