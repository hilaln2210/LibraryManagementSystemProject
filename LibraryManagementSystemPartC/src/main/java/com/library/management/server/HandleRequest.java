package com.library.management.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.management.server.request.Request;
import com.library.management.server.response.Response;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HandleRequest implements Runnable {
    private Socket clientSocket;
    private ObjectMapper objectMapper;

    public HandleRequest(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            // Read the request JSON from the client
            String requestJson = in.readLine();
            Request request = objectMapper.readValue(requestJson, Request.class);

            // Process the request (this is a placeholder, replace with actual logic)
            Response response = processRequest(request);

            // Convert the response to JSON and send back to the client
            String responseJson = objectMapper.writeValueAsString(response);
            out.println(responseJson);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Response processRequest(Request request) {
        // Placeholder for request processing logic
        Response response = new Response();
        response.setResponseType("Processed: " + request.getRequestType());
        return response;
    }
}
