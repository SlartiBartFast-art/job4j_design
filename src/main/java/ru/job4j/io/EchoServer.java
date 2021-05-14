package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 0. Что такое Socket? [#4850 #198950]
 * Доработайте класса ru.job4j.io.EchoServer.
 * Если клиент отправлять запрос http://localhost:9000/?msg=BYE нужно завершить работу сервера.
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        //блок трай-катч-висресур
        // создаем сокет на стороне сервера, с прослушиваемым портом 9000
        try (ServerSocket server = new ServerSocket(9000)) { // порт 9000 мб любой свободный
            //accept ждет запроса от клиента, при получении запроса создает
            //объект сокет для общения с клиентом(передачи ему информации) через который программа будет
            // взаимодествовать с подключенным клиентом
            Socket socket = server.accept();
            //блок трай-вис-ресур(+ексепшин)
            //создаем поток вывода для клиентсокого сокета - туда будем сливать инфу
            try (Scanner in = new Scanner(socket.getInputStream());
                 var out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
                 // открываем поток клиетского сокета в буфер для получения информации от клиента
            ) {
                out.write("HTTP/1.1 200 OK\r\n\n");

                while (!server.isClosed()) { // пока сервер принудительно не закроют

                    var done = false;
                    while (!done && in.hasNextLine()) {
                        String line = in.nextLine();
                        out.println("Echo: " + line);
                        if (line.trim().equals("BYE")) {
                            done = true;
                            out.write("Server is closed");
                            server.close();
                            System.out.println("Zavershenie seansa! ");
                        }
                    }
                }
            }
        }
    }
}
