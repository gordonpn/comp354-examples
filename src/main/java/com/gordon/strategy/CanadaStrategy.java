package com.gordon.strategy;

public class CanadaStrategy implements NorthAmericanStrategy {
  private final Province province;

  public CanadaStrategy(Province province) {
    this.province = province;
  }

  @Override
  public double getSalesTax() {
    return province.tax;
  }
}
