package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Топик : 2.3.4. JDBC
 * 0. JDBC [#6863]
 * Добавьте зависимость в проект
 * Доработайте код программы чтобы чтение url, имени пользователя и пароля
 * происходило из файла app.properties(в данном случае дял задание используется файл connDemoApp.properties)
 * Залейте код в репозиторий
 */
public class ConnectionDemo {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public ConnectionDemo(final String path) {
        this.path = path;
    }

    /**
     * Метод проводит запись данный в Мар из файла,
     * используемого при создании экземпляра класса в конструкторе.
     * разбивает данные на пару ключ\значение
     */
    public void load() {
        try (BufferedReader buffer = new BufferedReader(new FileReader(this.path))) {
            buffer.lines().filter(x -> x.contains("=") && !x.startsWith("#"))
                    .forEach(split -> {
                        String[] spliter = split.split("=");
                        if (spliter.length <= 1) {
                            throw new IllegalArgumentException();
                        } else {
                            values.put(spliter[0], spliter[1]);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает value соотвествующее заданному в параметре ключу.
     *
     * @param key ключ для поиска
     * @return значение value соотвествующее данному ключу в Map<String, String> values;
     */
    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
      /*  Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "PilotS";
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }*/

        ConnectionDemo connectionDemo = new ConnectionDemo("connDemoApp.properties");
        connectionDemo.load();
        Class.forName(connectionDemo.value("hibernate.connection.driver_class"));
        String url = connectionDemo.value("hibernate.connection.url");
        String login = connectionDemo.value("hibernate.connection.username");
        String password = connectionDemo.value("hibernate.connection.password");
        //String url = "jdbc:postgresql://localhost:5432/idea_db";
        // String login = "postgres";
        // String password = "password";
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
