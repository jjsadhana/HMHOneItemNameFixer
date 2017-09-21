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
    private DatabaseConfig dbConfig;

    private Connection reportingConn;

    private Connection scoringConn;

    public DatabaseConnection() {

    }

    public Connection getReportingConnection()  throws SQLException {

        reportingConn = DriverManager.getConnection(
                    dbConfig.getReportingUrl(),
                    dbConfig.getReportingUsername(),
                    dbConfig.getReportingPassword()
                    );

        if (reportingConn == null) {
            System.out.println("reportingConn Not successful....");
        } else {
            System.out.println("reportingConn Successful...." + reportingConn );
        }
        return reportingConn;
    }

    public Connection getScoringConnection()  throws SQLException {

            scoringConn = DriverManager.getConnection(
                    dbConfig.getScoringUrl(),
                    dbConfig.getScoringUsername(),
                    dbConfig.getScoringPassword()
            );
        if (scoringConn == null) {
            System.out.println("scoringConn Not successful....");
        } else {
            System.out.println("scoringConn  Successful...." + scoringConn );
        }
        return scoringConn;
    }

    public  Connection getConnection(ConnectionType type) {
        Connection connection = null;
        try {
            if(ConnectionType.SCORING == type ) {
                if (scoringConn == null || scoringConn.isClosed()) {
                    connection = getScoringConnection();
                }
            }else if(ConnectionType.REPORTING == type) {
                if (reportingConn == null || reportingConn.isClosed()) {
                    connection = getReportingConnection();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
