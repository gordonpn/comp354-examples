package com.gordon.controller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.net.URLDecoder.decode;

public class ControllerHelper {
  public static Map<String, String> queryToMap(HttpExchange httpExchange) {
    String query = httpExchange.getRequestURI().getRawQuery();

    if (query == null) {
      return Collections.emptyMap();
    }

    Map<String, String> result = new HashMap<>();
    for (String param : query.split("&")) {
      param = decode(param, StandardCharsets.UTF_8);
      String[] entry = param.split("=");
      if (entry.length > 1) {
        result.put(entry[0], entry[1]);
      } else {
        result.put(entry[0], "");
      }
    }
    return result;
  }

  public static void logResponse(HttpExchange exchange) {
    logResponse(exchange, System.out);
  }

  public static void logResponse(HttpExchange exchange, OutputStream output) {
    String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ssZ";

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);

    String dateString = simpleDateFormat.format(new Date());
    String requestMethod = exchange.getRequestMethod();
    URI requestURI = exchange.getRequestURI();
    String query = requestURI.getQuery();

    try {
      output.write(
          String.format("%n%s %s %s %s", dateString, requestMethod, requestURI, query).getBytes());
    } catch (IOException e) {
      System.err.println("Could not write to Output Stream: " + e.getMessage());
    }
  }
}
