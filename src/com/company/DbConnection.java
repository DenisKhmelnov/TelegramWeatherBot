package com.company;

import jdk.jshell.JShell;
import org.telegram.telegrambots.api.objects.Message;

import java.sql.*;

public class DbConnection {
    public Connection connection = null;
    public DbConnection() throws SQLException {
        connection=null;
        String url = "jdbc:sqlite:BotDb.sqlite";

        try{
            connection = DriverManager.getConnection(url);
        }
        catch (Exception e){
            System.err.print(e.getMessage());
            e.printStackTrace();
            return;
        }
        System.out.println("Opened database successfully");
    }

    public void insertSubscription(Message userMessage) throws SQLException{
        if(userMessage.hasLocation()){
            long chatId = userMessage.getChatId();
            float lat = userMessage.getLocation().getLatitude();
            float lon = userMessage.getLocation().getLongitude();
            PreparedStatement stm;
            String sqlInsert = "INSERT INTO Subscription (chatId, lat, lon) VALUES (?,?,?);";
            stm = connection.prepareStatement(sqlInsert);
            stm.setLong(1, chatId);
            stm.setFloat(2,lat);
            stm.setFloat(3,lon);
            stm.executeUpdate();
            connection.close();
        }
        else if(userMessage.hasText()) {
            long chatId = userMessage.getChatId();
            String city = userMessage.getText();
            PreparedStatement stm;
            String sqlInsert = "INSERT INTO Subscription (chatId, city) VALUES (?,?);";
            stm = connection.prepareStatement(sqlInsert);
            stm.setLong(1, chatId);
            stm.setString(2,city);
            stm.executeUpdate();
            stm.close();
        }
    }

    public void removeSubscription(Message userMessage) throws SQLException {
        long chatId = userMessage.getChatId();
        PreparedStatement stm;
        String sqlRemove = "DELETE FROM Subscription WHERE chatId = ?;";
        stm = connection.prepareStatement(sqlRemove);
        stm.setLong(1,chatId);
        stm.execute();
        stm.close();
    }
}
