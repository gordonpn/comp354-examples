package com.gordon.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import static com.gordon.controller.ControllerHelper.logResponse;
import static com.gordon.view.ProductView.render;

public class IndexHandler implements HttpHandler {
  @Override
  public void handle(HttpExchange exchange) throws IOException {
    logResponse(exchange);
    render(exchange, "Index Page");
  }
}
