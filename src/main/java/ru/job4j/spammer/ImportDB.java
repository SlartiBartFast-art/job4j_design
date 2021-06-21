package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 3. Загрузка базы спамеров. [#20459]
 * Топик : 2.3.4. JDBC
 * У нас появился клиент, который хочет создать базу данных для спамеров.
 * * На рынке ему продали диск в котором находятся txt файлы.
 * Клиент просит перевести эти файлы в базу данных PostgreSQL.
 * Реализуйте класс ImportDB.
 */
public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     * Метод проводит чтение из фала *.txt(в нашем случае dump.txt) данных,
     * запись эти данных в List<>
     * @return List<User> объектов
     */
    public List<User> load() {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(str -> {
                String[] string = str.split(";");
                if (string.length <= 1) {
                    throw new IllegalArgumentException();
                } else {
                    System.out.println("To chto zashlo : " + string[0] + "  Key " + string[1]);
                    users.add(new User(string[0], string[1]));
                }
            }); //разделить на два значения записать в юзера
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Иетод проводит запись в БД List<User> объектов в указанную в запросе таблицу
     * @param users объекты для записи в БД
     * @throws ClassNotFoundException
     */
    public void save(List<User> users) throws ClassNotFoundException {
        String driver = cfg.getProperty("jdbc.driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver is not found");
            e.printStackTrace();
        }
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users (name, email) values(?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("./spammerApp.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}
