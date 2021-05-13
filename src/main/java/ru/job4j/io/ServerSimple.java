package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 0. Что такое Socket?
 * открывается в гугле с надписью на странице Hello
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
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                    }
                    out.write("HTTP/1.1 200 OK\r\n\n".getBytes());
                    out.write("Hello".getBytes());
                }
            }
        }
    }
}
