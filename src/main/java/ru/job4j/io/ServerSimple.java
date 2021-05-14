package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 0. Что такое Socket?
 * 1. Бот [#7921 #198951]
 * при обращании к локальному хосту http://localhost:9000/?msg=Hello через браузер
 * открывается в браузере с надписью на странице Hello, dear friend
 * Доработайте класс ru.job4j.io.EchoServer.
 * msg=Hello > Hello.
 * msg=Exit > Завершить работу сервера.
 * msg=What > What.
 */
public class ServerSimple {
    public static void main(String[] args) throws IOException {
      /* ServerSocket serverSocket = new ServerSocket(9000);
        Socket clientSocket = serverSocket.accept();
        clientSocket.getOutputStream().write("HTTP/1.1 200 OK\\r\\n\\".getBytes());
        clientSocket.close();
        serverSocket.close();*/
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\n".getBytes());

                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.contains("Hello")) {
                            out.write("\r\n\n".getBytes());
                            out.write("Hello, dear friend.".getBytes());
                            out.flush();
                        }
                        if (str.contains("Exit")) {
                            out.write("\r\n\n".getBytes());
                            out.write("Server is closed.".getBytes());
                            server.close();
                        }
                        if (str.contains("What")) {
                            out.write("\r\n\n".getBytes());
                            out.write("What".getBytes());
                            out.flush();
                        }
                    }
                }
            }
        }
    }
}
