package com.gordon.service.external;

/**
 * Pretend that this is an external third party method that we do not have access to, and we must
 * use for whatever reason
 */
public class IntlSalesTaxService {
  public double getSalesTaxFor(String europeanCountry) {
    if (europeanCountry.equalsIgnoreCase("sweden")
        || europeanCountry.equalsIgnoreCase("norway")
        || europeanCountry.equalsIgnoreCase("denmark")) {
      return 25.0;
    }
    throw new UnsupportedOperationException();
  }
}
