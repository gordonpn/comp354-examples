package com.gordon.service;

public class SalesTaxService {
  private static SalesTaxService salesTaxService;

  private SalesTaxService() {
    // prevent instantiation
  }

  public static SalesTaxService getInstance() {
    if (salesTaxService == null) {
      salesTaxService = new SalesTaxService();
    }

    return salesTaxService;
  }

  public double getPriceWithTax(Province province, int priceBeforeTax) {
    return priceBeforeTax + (priceBeforeTax * province.tax);
  }
}
