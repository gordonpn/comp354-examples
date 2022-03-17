package com.gordon.subject;

import java.util.ArrayList;
import java.util.List;

public class StockLevel {
  List<Integer> stockHistory;

  public StockLevel() {
    this.stockHistory = new ArrayList<>();
    this.stockHistory.add(0);
  }

  public void add(int stock) {
    this.stockHistory.add(stock);
  }

  public int getCurrentStock() {
    return stockHistory.get(stockHistory.size() - 1);
  }

  public int getPreviousStock() {
    return stockHistory.get(stockHistory.size() - 2);
  }

  @Override
  public String toString() {
    return "StockLevel{" + "stockHistory=" + stockHistory + '}';
  }
}
