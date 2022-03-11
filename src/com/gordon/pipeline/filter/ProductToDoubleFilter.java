package com.gordon.pipeline.filter;

import com.gordon.subject.Product;

public class ProductToDoubleFilter implements Filter<Product, Double> {
  @Override
  public Double process(Product input) {
    return (double) input.getPrice().getCurrentPrice();
  }
}
