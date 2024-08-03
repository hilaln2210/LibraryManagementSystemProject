package com.library.management.server;

public class ServerDriver {
    public static void main(String[] args) {
        try {
            Server server = new Server(8080);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
