package com.hmhco.scoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.config.server.EnableConfigServer;
import java.sql.Connection;

/**
 * Created by jayachandranj on 9/20/17.
 */

@SpringBootApplication
@Configuration
@ComponentScan("com.hmhco.scoring")

@EnableConfigServer
public class ItemNameFixer implements CommandLineRunner {

    @Autowired
    private DatabaseConnection connection;
    @Autowired
    private DatabaseConfig databaseConfig;

    @Override
    public void run(String... args) throws Exception {
       Connection con;
        /*try {
            con = connection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
        SpringApplication.run(ItemNameFixer.class, args);
    }


    public static void main(String[] args) {
        SpringApplication.run(ItemNameFixer.class, args);
    }



}
