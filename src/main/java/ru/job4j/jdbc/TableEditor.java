package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Топик : 2.3.4. JDBC
 * 0.1. Statement [#379306]
 * Дан каркас класса. Реализовать его методы
 * Чтение настроек должно идти из файла *.properties
 */
public class TableEditor implements AutoCloseable {
    private Connection connection; //соединение
    private Properties properties; //файл хранящий ключ/значение

    public TableEditor(Properties properties) { // передали в него данный из файла
        this.properties = properties;
        initConnection();
    }

    /**
     * Метод проводит чтение данных из файла *.properties
     * прописывает необходимые значения(key-value) для установки соединения
     * говорит какой именно драйвер необходимо загрузить
     * проводит загрузку драйвера
     */
    private void initConnection() {
        String driver = properties.getProperty("hibernate.connection.driver_class");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver is not found");
            e.printStackTrace();
        }
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            System.out.println("Connection Failed : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Метод устанавливает соединение + создает объект для внесния изминений в базу
     * @param string запрос/действие которое необходимо провести в таблице
     */
    private void setConnection(String string) {
        if (connection != null) {
            try (Statement statement = connection.createStatement()) {
                String sql = string;
                System.out.println(sql);
                statement.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод производит создание таблицы с указанным именем
     *
     * @param tableName имя таблицы которую мы создаем
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void createTable(String tableName) throws SQLException, ClassNotFoundException {
        String sql = String.format( // запрос в БД
                "create table if not exists %s();", tableName
        );
        setConnection(sql);
    }

    /**
     * Меод производит удаление таблицы из БД
     *
     * @param tableName имя таблицы для удаления
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void dropTable(String tableName) throws SQLException, ClassNotFoundException {
        String sql = String.format( // запрос в БД
                "drop table if exists %s;", tableName
        );
        setConnection(sql);
    }

    /**
     * Метод производит добавление колонки в таблицу
     *
     * @param tableName  имя таблицы куда производится добавление колонки
     * @param columnName имя колонки
     * @param type       параметр хранимого занчения
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addColumn(String tableName, String columnName, String type) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "alter table if exists %s add column %s %s;",
                tableName, columnName, type
        );
        setConnection(sql);
    }

    /**
     * Метод производит удаление колонки в указываемой таблице
     *
     * @param tableName  таблица в БД в которой производится удаление колонки
     * @param columnName удаляемая колонка в таблице
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void dropColumn(String tableName, String columnName) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "alter table if exists %s drop column %s;",
                tableName, columnName
        );
        setConnection(sql);
    }

    /**
     * Метод производит изминение/переименование колонки в таблице
     * @param tableName     имя таблицы в БД в которой будет происходит смена колонки
     * @param columnName    имя колонки для переименования
     * @param newColumnName новое имя колонки после переименования
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "alter table if exists %s rename column %s to %s;",
                tableName, columnName, newColumnName
        );
        setConnection(sql);
    }

    /**
     * Метод выводит схему таблицы, а именно ее столбцы и их типы
     * @param tableName tableName таблицы котороую мы хотим вывести
     * @return строковое представление таблицы
     * @throws SQLException
     */
    public String getScheme(String tableName) throws SQLException, ClassNotFoundException {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, "%")) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("connDemoApp.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            TableEditor tableEditor = new TableEditor(properties);
            tableEditor.createTable("warlon");
            System.out.println(tableEditor.getScheme("warlon"));
            tableEditor.addColumn("warlon", "name", "varchar (250)");
            System.out.println(tableEditor.getScheme("warlon"));
            tableEditor.addColumn("warlon", "ego", "varchar (50)");
            System.out.println(tableEditor.getScheme("warlon"));
            tableEditor.dropColumn("warlon", "name");
            System.out.println(tableEditor.getScheme("warlon"));
            tableEditor.renameColumn("warlon", "ego", "black");
            System.out.println(tableEditor.getScheme("warlon"));
            tableEditor.dropTable("warlon");
            System.out.println(tableEditor.getScheme("warlon"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
