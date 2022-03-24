package com.gordon;

import com.gordon.adapter.Country;
import com.gordon.adapter.IntlSalesTaxServiceAdapter;
import com.gordon.adapter.SalesTax;
import com.gordon.service.SalesTaxService;
import com.gordon.service.external.IntlSalesTaxService;
import com.gordon.strategy.CanadaStrategy;
import com.gordon.strategy.CountryStrategy;
import com.gordon.strategy.Province;
import com.gordon.strategy.States;
import com.gordon.strategy.UnitedStatesStrategy;
import com.gordon.subject.skateboard.Deck;

public class AdapterExample {
  public static void main(String[] args) {
    Deck deck = new Deck(50);

    System.out.printf("%nTotal value of skateboard: %d%n", deck.getTotalValue());
    SalesTax salesTaxService = SalesTaxService.getInstance();

    salesTaxService.setStrategy(new UnitedStatesStrategy(States.CALIFORNIA));
    System.out.printf(
        "Price after tax of skateboard: %.2f%n",
        salesTaxService.getPriceWithTax(deck.getTotalValue()));

    salesTaxService.setStrategy(new CanadaStrategy(Province.QUEBEC));
    System.out.printf(
        "Price after tax of skateboard: %.2f%n",
        salesTaxService.getPriceWithTax(deck.getTotalValue()));

    salesTaxService = new IntlSalesTaxServiceAdapter(new IntlSalesTaxService());
    salesTaxService.setStrategy(new CountryStrategy(Country.SWEDEN));
    System.out.printf(
        "Price after tax of skateboard: %.2f%n",
        salesTaxService.getPriceWithTax(deck.getTotalValue()));
  }
}
