package com.jcourse.lab5Server.Main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private static final int DEFAULT_PORT = 4041;

    public static void main(String[] args) {
        int port = (args.length > 0) ? Integer.parseInt(args[0]) : DEFAULT_PORT;
        if (port < 1025 || port > 65535){
            port = DEFAULT_PORT;
            System.out.println("Warning: Wrong port number! Default port: " + port);
        }
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port: " + serverSocket.getLocalPort() + "\n");
            while (true) {
                System.out.println("Waiting for a client...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connect: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                new Thread(new ClientSession(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to establish connection!");
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}
