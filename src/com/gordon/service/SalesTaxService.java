package com.gordon.service;

import com.gordon.adapter.SalesTax;
import com.gordon.strategy.NorthAmericanStrategy;

/**
 * This class' original purpose was to demonstrate how to implement the singleton pattern. Later, it
 * was expanded to demonstrate the strategy pattern as well, but this is actually an antipattern
 * because singleton classes should not allow client classes to change its state, the state of
 * singleton should usually be self-managed
 */
public class SalesTaxService implements SalesTax<NorthAmericanStrategy> {
  private static SalesTaxService salesTaxService;
  private NorthAmericanStrategy strategy;

  private SalesTaxService() {
    // prevent instantiation
  }

  /** Provides client classes with an instance of the singleton
   * @return an instance of SalesTaxService
   */
  public static SalesTaxService getInstance() {
    if (salesTaxService == null) {
      salesTaxService = new SalesTaxService();
    }

    return salesTaxService;
  }

  /** Return the price with tax for the given price before taxes using the strategy's tax rate
   * @param priceBeforeTax price before tax
   * @return price with taxes
   */
  @Override
  public double getPriceWithTax(int priceBeforeTax) {
    return priceBeforeTax + (priceBeforeTax * strategy.getSalesTax());
  }

  @Override
  public void setStrategy(NorthAmericanStrategy strategy) {
    this.strategy = strategy;

  }
}
