package com.gordon.controller;

import com.gordon.repository.ProductRepository;
import com.gordon.singleton.DaoSingletonFactory;
import com.gordon.subject.Product;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.gordon.controller.ControllerHelper.logResponse;
import static com.gordon.controller.ControllerHelper.queryToMap;
import static com.gordon.view.ProductView.render;

public class ProductsHandler implements HttpHandler {
  ProductRepository productRepository;

  public ProductsHandler(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public ProductsHandler() {
    productRepository = DaoSingletonFactory.getInstance("products");
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    logResponse(exchange);
    Map<String, String> paramsMap = queryToMap(exchange);

    if (paramsMap.containsKey("name")) {

      if ("get".equalsIgnoreCase(exchange.getRequestMethod())) {
        handleOneProduct(exchange, paramsMap.get("name"));
      } else if ("put".equalsIgnoreCase(exchange.getRequestMethod())
          && paramsMap.containsKey("price")) {
        handleProductPriceUpdate(exchange, paramsMap.get("name"), paramsMap.get("price"));
      }

    } else {
      handleAllProducts(exchange);
    }
  }

  private void handleProductPriceUpdate(HttpExchange exchange, String name, String price)
      throws IOException {
    Product product = productRepository.updatePrice(name, Integer.parseInt(price));
    render(exchange, String.valueOf(product));
  }

  private void handleAllProducts(HttpExchange exchange) throws IOException {
    List<Product> products = productRepository.getAll();
    render(exchange, String.valueOf(products));
  }

  void handleOneProduct(HttpExchange exchange, String name) throws IOException {
    Product product = productRepository.getByName(name);
    render(exchange, String.valueOf(product));
  }
}
