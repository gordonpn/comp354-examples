package com.gordon.pipeline.filter;

public class TaxesFilter implements Filter<Double, Double> {
  private final double taxRate;

  public TaxesFilter(double taxRate) {
    this.taxRate = taxRate;
  }

  @Override
  public Double process(Double input) {
    return (input * taxRate) + input;
  }
}
