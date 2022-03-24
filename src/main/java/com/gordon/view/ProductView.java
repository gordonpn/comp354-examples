package com.gordon.view;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ProductView {
  public static void render(HttpExchange exchange, String string) throws IOException {
    OutputStream outputStream = exchange.getResponseBody();
    String htmlResponse = String.format("<html><body><h1>%s</h1></body></html>", string);
    exchange.sendResponseHeaders(200, htmlResponse.length());
    outputStream.write(htmlResponse.getBytes());
    outputStream.flush();
    outputStream.close();
  }
}
