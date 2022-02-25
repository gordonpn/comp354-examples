package com.gordon.strategy;

public enum Province {
  QUEBEC(0.15),
  ALBERTA(0.05);

  public final double tax;

  Province(double tax) {
    this.tax = tax;
  }
}
