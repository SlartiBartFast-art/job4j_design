package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * экземпляр класса Properties,  его работа
 */
public class Temp {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        //properties.load(new FileInputStream("connDemoApp.properties"));
        properties.load(new FileInputStream ("connDemoApp.properties"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        System.out.println(url);
        System.out.println(login);
        System.out.println(password);
    }

}
