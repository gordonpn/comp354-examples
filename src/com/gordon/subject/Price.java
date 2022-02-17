package com.gordon.subject;

import java.util.ArrayList;
import java.util.List;

public class Price {
  List<Integer> priceHistory;

  public Price() {
    this.priceHistory = new ArrayList<>();
    this.priceHistory.add(0);
  }

  public void add(int price) {
    this.priceHistory.add(price);
  }

  public int getLowestPrice() {
    return this.priceHistory.stream().mapToInt(Integer::intValue).min().getAsInt();
  }

  public int getCurrentPrice() {
    return this.priceHistory.get(this.priceHistory.size() - 1);
  }
}
