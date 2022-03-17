package com.gordon.singleton;

import com.gordon.repository.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class DaoSingletonFactory {
  private static Map<String, ProductRepository> repositories;

  public static ProductRepository getInstance(String repositoryName) {
    if (repositories == null) {
      repositories = new HashMap<>();
    }

    if (repositories.get(repositoryName) == null) {
      ProductRepository p = new ProductRepository();
      repositories.put(repositoryName, p);
    }

    return repositories.get(repositoryName);
  }
}
