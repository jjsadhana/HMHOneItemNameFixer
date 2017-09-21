package com.hmhco.scoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

/**
 * Created by jayachandranj on 9/20/17.
 */

@SpringBootApplication
@Configuration
@ComponentScan("com.hmhco.scoring")
//@EnableConfigServer
public class ItemNameFixer implements CommandLineRunner {
    @Autowired
    private DatabaseConnection databaseConnection;

    public static void main(String[] args) throws Exception {

        SpringApplication.run(ItemNameFixer.class, args);

    }

    //access command line arguments
    @Override
    public void run(String... args) throws Exception {

        //TODO -fetchActivities for REsourceId

        ReportingUtil reportingUtil = new ReportingUtil(databaseConnection.getConnection(ConnectionType.REPORTING));
        List<UUID> activities = reportingUtil.getActivities("JY_NA17E_OA_G04U01L01D05SBW_000");

        ScoringUtil scoringUtil = new ScoringUtil(databaseConnection.getConnection(ConnectionType.SCORING));
        scoringUtil.getSessionIds(activities);
        System.exit(0);

    }


}
