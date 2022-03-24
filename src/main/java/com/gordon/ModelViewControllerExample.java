package com.gordon;

import com.gordon.controller.IndexHandler;
import com.gordon.controller.ProductsHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ModelViewControllerExample {
  public static void main(String[] args) throws IOException {
    final int port = 8000;
    InetSocketAddress address = new InetSocketAddress(port);
    HttpServer server = HttpServer.create(address, 0);

    server.createContext("/", new IndexHandler());
    server.createContext("/products", new ProductsHandler());

    ThreadPoolExecutor threadPool =
        new ThreadPoolExecutor(
            5,
            5,
            5,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            r -> new Thread(r, "MVC-Thread"));
    server.setExecutor(threadPool);

    server.start();
    System.out.printf("Server started on http://localhost:%d", port);
    // http PUT localhost:8000/products name==nike price==30
  }
}
