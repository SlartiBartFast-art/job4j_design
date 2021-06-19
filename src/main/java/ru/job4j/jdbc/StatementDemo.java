package ru.job4j.jdbc;

import java.sql.*; // импортируем пакет JDBC

/**
 * 0.1. Statement [#379306 #168426]
 * Топик : 2.3.4. JDBC
 * Пример , с комментариями
 */
public class StatementDemo {

    /**
     * подключение к БД
     *
     * @return
     * @throws Exception
     */
    private static Connection getConnection() throws Exception {
        // Мы используем метод Class.forName() для динамической загрузки класса драйвера в память,
        // после чего происходит его автоматическая регистрация.
        Class.forName("org.postgresql.Driver"); // регистрация JDBC драйвера
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "PilotS";
        return DriverManager.getConnection(url, login, password); // установление соединения с БД
    }

    public static void main(String[] args) throws Exception {
        try (Connection connection = getConnection()) {
            //Statement - Этот интерфейс используется для доступа к БД для общих целей.
            //Прежде, чем мы сможем использовать экземпляр Statement для выполнения SQL – запросов,
            // нам необходимо создать такой экземпляр. ДЛя этого используется метод Connection.createStatement().
            //После этго мы можем использовать наш экземпляр statement для выполнения SQL – запросов.
            try (Statement statement = connection.createStatement()) {
                String sql = String.format( // запрос в БД
                        "create table if not exists demo_table(%s, %s);",
                        "id serial primary key",
                        "name varchar(255)"
                );
                //boolean execute (String SQL)
                //Этот метод возвращает логическое значение true, если объект ResultSet может быть получен.
                // В противном случае он вовращает false. Он используется для выполнения DDL SQL – запросов
                // ил в случаях, когда мы используем динамический SQL.
                System.out.println("То что прошлол в запросе: " + sql);
                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));
            }
        }
    }

    /**
     * Метод проверяет что таблица создалась, выводит столбцы и их типы
     *
     * @param connection
     * @param tableName
     * @return
     * @throws Exception
     */
    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }
}

