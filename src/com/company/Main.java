package com.company;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main {

    public static void main(String[] args) throws SQLException {
	// write your code here
        ApiContextInitializer.init();
        TelegramBotsApi botapi = new TelegramBotsApi();
        try{
            botapi.registerBot(new Bot());
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
