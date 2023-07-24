package com.project.springboot.plantidati.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;

// Double check the Database being used.
@RestController
public class DatabaseRestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/database-info")
    public String getDatabaseInfo() throws SQLException {
        return "URL: " + dataSource.getConnection().getMetaData().getURL() + ", " +
                "Username: " + dataSource.getConnection().getMetaData().getUserName();
    }
}
