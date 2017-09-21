package com.hmhco.scoring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by jayachandranj on 9/21/17.
 */
public class ScoringUtil {

    private Connection connection;
    private ProcessorInvoker processorInvoker;

    public ScoringUtil(Connection con) {
        this.connection=con;
    }

    public void updateItemName(List<UUID> activities){

    }

    public void  getSessionIds(List<UUID> activities){

        //fetch activities by ResourceId
        System.out.println("activities size..."+ activities.size());
        List<UUID> sessions = new ArrayList<>();
        String result = convertListToString(activities);
        String sql = "Select distinct ais.session_id  from assignment_item_scores ais join sessions s on ais.session_id = s.session_id  where activity_refid  in ("+result+") and ais.item_name is null ";

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);

            while(!resultset.wasNull() && resultset.next()) {
                sessions.add(UUID.fromString(resultset.getString("session_id")));
            }

            System.out.println("List of sessions size..."+ sessions.size());
            processorInvoker = new ProcessorInvoker();
            processorInvoker.ConvertIntoChunks(sessions);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectionClose(connection,statement,resultset);
        }


    }

    private String convertListToString(List<UUID> activities){
        String result = activities.stream()
                .map((s) -> "\'" + s + "\'")
                .collect(Collectors.joining(", "));

        return result;
    }

    private void connectionClose(Connection connection, Statement statement, ResultSet resultset){
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
