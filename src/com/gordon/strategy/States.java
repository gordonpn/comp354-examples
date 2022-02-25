package com.gordon.strategy;

public enum States {
  CALIFORNIA(0.0725);

  public final double tax;

  States(double tax) {
    this.tax = tax;
  }
}
