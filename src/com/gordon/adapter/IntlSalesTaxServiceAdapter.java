package com.gordon.adapter;

import com.gordon.service.external.IntlSalesTaxService;
import com.gordon.strategy.IntlStrategy;

/** We create an adapter to work with an "external" API that is incompatible with ours */
public class IntlSalesTaxServiceAdapter implements SalesTax<IntlStrategy> {
  private final IntlSalesTaxService salesTaxService;
  private IntlStrategy strategy;

  public IntlSalesTaxServiceAdapter(IntlSalesTaxService salesTaxService) {
    this.salesTaxService = salesTaxService;
  }

  @Override
  public double getPriceWithTax(int priceBeforeTax) {
    return priceBeforeTax + ((priceBeforeTax * salesTaxService.getSalesTaxFor(strategy.getName())) / 100);
  }

  @Override
  public void setStrategy(IntlStrategy strategy) {
    this.strategy = strategy;
  }
}
