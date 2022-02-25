package com.gordon.strategy;

public class UnitedStatesStrategy implements NorthAmericanStrategy {
  private final States states;

  public UnitedStatesStrategy(States states) {
    this.states = states;
  }

  @Override
  public double getSalesTax() {
    return states.tax;
  }
}
