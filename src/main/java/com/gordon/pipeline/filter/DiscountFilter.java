package com.gordon.pipeline.filter;

public class DiscountFilter implements Filter<Double, Double> {
  private final int percentageDiscount;

  public DiscountFilter(int percentageDiscount) {
    this.percentageDiscount = percentageDiscount;
  }

  @Override
  public Double process(Double input) {
    return input * ((double) percentageDiscount / 100);
  }
}
