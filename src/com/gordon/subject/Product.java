package com.gordon.subject;

public class Product {
  Price price;
  StockLevel stockLevel;

  public Product() {
    this.price = new Price();
    this.stockLevel = new StockLevel();
  }

  public void updateStock(int newStock) {
    this.stockLevel.add(newStock);
  }

  public void updatePrice(int newPrice) {
    this.price.add(newPrice);
  }
}
