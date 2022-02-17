package com.gordon.service;

public enum Province {
  QUEBEC(0.15, "Quebec"),
  ALBERTA(0.05, "Alberta");

  public final double tax;
  public final String fullName;

  Province(double tax, String fullName) {
    this.tax = tax;
    this.fullName = fullName;
  }
}
