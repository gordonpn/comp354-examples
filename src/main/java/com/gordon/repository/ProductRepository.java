package com.gordon.repository;

import com.gordon.subject.Product;
import com.gordon.subject.Sneaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
  private final Map<String, Product> productsDb;

  public ProductRepository() {
    productsDb = new HashMap<>();
    Sneaker nike = new Sneaker();
    nike.updatePrice(10);
    productsDb.put("nike", nike);
  }

  public Product getByName(String name) {
    return productsDb.get(name);
  }

  public List<Product> getAll() {
    return new ArrayList<>(productsDb.values());
  }

  public Product updatePrice(String name, int newPrice) {
    Product product = productsDb.get(name);
    product.updatePrice(newPrice);
    return product;
  }
}
