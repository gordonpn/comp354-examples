package com.gordon.adapter;

import com.gordon.strategy.Strategy;

public interface SalesTax<T extends Strategy> {
  double getPriceWithTax(int priceBeforeTax);
  void setStrategy(T strategy);
}
