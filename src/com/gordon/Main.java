package com.gordon;

import com.gordon.adapter.Country;
import com.gordon.adapter.IntlSalesTaxServiceAdapter;
import com.gordon.adapter.SalesTax;
import com.gordon.observer.EmailObserver;
import com.gordon.observer.SmsObserver;
import com.gordon.service.SalesTaxService;
import com.gordon.service.external.IntlSalesTaxService;
import com.gordon.strategy.*;
import com.gordon.subject.Sneaker;
import com.gordon.subject.skateboard.Deck;
import com.gordon.subject.skateboard.Trucks;
import com.gordon.subject.skateboard.Wheels;

public class Main {
  public static void main(String[] args) {
    Sneaker sneaker = new Sneaker();
    SmsObserver smsObserver = new SmsObserver("5140000000");
    EmailObserver emailObserver = new EmailObserver("gordon@concordia.ca");

    sneaker.subscribe(smsObserver);
    sneaker.updateStock(9);

    sneaker.unsubscribe(smsObserver);
    sneaker.updateStock(90);

    sneaker.subscribe(smsObserver);
    sneaker.updateStock(0);
    sneaker.subscribe(emailObserver);
    sneaker.updateStock(10);
    sneaker.updatePrice(10);

    Wheels wheels = new Wheels(10);
    Trucks trucks = new Trucks(20);
    trucks.addPart(wheels);
    trucks.addPart(wheels);

    Deck deck = new Deck(50);
    deck.addPart(trucks);
    deck.addPart(trucks);

    System.out.printf("%nTotal value of skateboard: %d%n", deck.getTotalValue());
    SalesTax salesTaxService = SalesTaxService.getInstance();

    salesTaxService.setStrategy(new UnitedStatesStrategy(States.CALIFORNIA));
    System.out.printf("Price after tax of skateboard: %.2f%n", salesTaxService.getPriceWithTax(deck.getTotalValue()));

    salesTaxService.setStrategy(new CanadaStrategy(Province.QUEBEC));
    System.out.printf("Price after tax of skateboard: %.2f%n", salesTaxService.getPriceWithTax(deck.getTotalValue()));

    salesTaxService = new IntlSalesTaxServiceAdapter(new IntlSalesTaxService());
    salesTaxService.setStrategy(new CountryStrategy(Country.SWEDEN));
    System.out.printf("Price after tax of skateboard: %.2f%n", salesTaxService.getPriceWithTax(deck.getTotalValue()));
  }
}
