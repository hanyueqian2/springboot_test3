package com.han.test.springboot_test3.wang.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {

    private static final String RABBIT_HOST = "192.168.102.138";
    private static final String RABBIT_USERNAME = "tony";
    private static final String RABBIT_PASSWORD = "tony";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            connection = getNewConnection();
        }
        return connection;
    }

    public static Connection getNewConnection() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RABBIT_HOST);
        connectionFactory.setUsername(RABBIT_USERNAME);
        connectionFactory.setPassword(RABBIT_PASSWORD);
        connection = null;
        try {
            connection = connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return connection;
    }


}