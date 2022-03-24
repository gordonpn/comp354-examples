package com.gordon.message;

import com.gordon.subject.StockLevel;

public class StockMessage implements Message {
  private final StockLevel stockLevel;

  public StockMessage(StockLevel stockLevel) {
    this.stockLevel = stockLevel;
  }

  @Override
  public String generateMessage() {
    return String.format(
        "New stock level: %s%n\tPrevious stock level: %s%n",
        this.stockLevel.getCurrentStock(), this.stockLevel.getPreviousStock());
  }
}
