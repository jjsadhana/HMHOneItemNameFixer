package com.hmhco.scoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jayachandranj on 9/21/17.
 */

public class ReportingUtil {

    private Connection connection;


    public ReportingUtil(Connection con) {
        this.connection=con;
    }

    public List<UUID>  getActivities(String resourceId){

        //fetch activities by ResourceId
        System.out.println("resourceId..."+ resourceId);
        List<UUID> activities = new ArrayList<>();

        String sql = "Select activity_id from activities  where resource_id ='"+resourceId+"'";

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);

            while(!resultset.wasNull() && resultset.next()) {
                activities.add(UUID.fromString(resultset.getString("activity_id")));
            }

            System.out.println("List of activities size..."+ activities.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectionClose(connection,statement,resultset);
        }

        return activities;
    }
    public void getActivities(UUID assignmentRefId){
        //fetch the activity id using assignmentId
    }

    public void getActivities(Date startDate, Date endDate){
        //fetch the activityId using startDate and endDate

        List<UUID> activities = new ArrayList<>();

        SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");

        String fromDate = "2017-09-01";
        String toDate   = "2017-09-30";

        String sql = "Select activity_id from activities  where test_type ='FORMATIVE'  and created_date >='"+fromDate+"' and updated_date <='"+toDate+"'";

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);

            while(!resultset.wasNull() && resultset.next()) {
                activities.add(UUID.fromString(resultset.getString("activity_id")));
            }

            System.out.println("List of activities size..."+ activities.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectionClose(connection,statement,resultset);
        }
    }

    private void connectionClose(Connection connection, Statement statement,ResultSet resultset){
        try {
            if(resultset!=null)
                resultset.close();
            if(statement!=null)
                statement.close();
            if(connection!=null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
