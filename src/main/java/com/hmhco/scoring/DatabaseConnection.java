package com.hmhco.scoring;

import com.hmhco.scoring.DatabaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by jayachandranj on 9/20/17.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class DatabaseConnection {


    @Autowired
    private DatabaseConfig databaseConfig;

    public DatabaseConnection() {
        this.databaseConfig = databaseConfig;
    }


    public Connection getConnection()  throws SQLException {
        Connection connection = null;
           connection = DriverManager.getConnection(
                    databaseConfig.getUrl(),
                    databaseConfig.getUsername(),
                    databaseConfig.getPassword());

                    if(connection ==null){
                        System.out.println("Not successful....");
                    }else{
                        System.out.println("Successful...." + connection);
                    }


        return connection;
    }
}
