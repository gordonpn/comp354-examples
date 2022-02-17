package com.gordon;

import com.gordon.subject.Price;

public class PriceMessage implements Message {
  private final Price price;

  public PriceMessage(Price price) {
    this.price = price;
  }

  @Override
  public String generateMessage() {
    return String.format("New price: %s%n\tAll time lowest price: %s%n", this.price.getCurrentPrice(), this.price.getLowestPrice());
  }
}
