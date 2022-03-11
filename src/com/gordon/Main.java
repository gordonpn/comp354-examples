package com.gordon;

import com.gordon.adapter.Country;
import com.gordon.adapter.IntlSalesTaxServiceAdapter;
import com.gordon.adapter.SalesTax;
import com.gordon.observer.EmailObserver;
import com.gordon.observer.SmsObserver;
import com.gordon.pipeline.Pipeline;
import com.gordon.pipeline.filter.DiscountFilter;
import com.gordon.pipeline.filter.LetterFilter;
import com.gordon.pipeline.filter.ProductToDoubleFilter;
import com.gordon.pipeline.filter.SpaceFilter;
import com.gordon.pipeline.filter.TaxesFilter;
import com.gordon.service.SalesTaxService;
import com.gordon.service.external.IntlSalesTaxService;
import com.gordon.strategy.CanadaStrategy;
import com.gordon.strategy.CountryStrategy;
import com.gordon.strategy.Province;
import com.gordon.strategy.States;
import com.gordon.strategy.UnitedStatesStrategy;
import com.gordon.subject.Product;
import com.gordon.subject.Sneaker;
import com.gordon.subject.skateboard.Deck;
import com.gordon.subject.skateboard.Trucks;
import com.gordon.subject.skateboard.Wheels;

public class Main {
  public static void main(String[] args) {

    //    new Main().observerExample();
    //
    //    Deck deck = new Main().compositeExample();
    //
    //    new Main().adapterExample(deck);
    //    new Main().pipelineExample();
    new Main().pipelineProductExample();
  }

  private void adapterExample(Deck deck) {
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

  private void observerExample() {
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
  }

  private Deck compositeExample() {
    Wheels wheels = new Wheels(10);
    Trucks trucks = new Trucks(20);
    trucks.addPart(wheels);
    trucks.addPart(wheels);

    Deck deck = new Deck(50);
    deck.addPart(trucks);
    deck.addPart(trucks);

    return deck;
  }

  private void pipelineExample() {
    String input = "1234 asdf 1234";
    Pipeline<String, String> pipeline = new Pipeline<>(new SpaceFilter());
    pipeline = pipeline.addPipe(new LetterFilter());

    String output = pipeline.process(input);

    System.out.println(output);

    //    System.out.println(input.replaceAll("\\s", "").replaceAll("[a-zA-Z]*", ""));
    //
    //    String streamOutput =
    //        input
    //            .chars()
    //            .filter(Character::isDigit)
    //            .mapToObj(val -> (char) val)
    //            .map(String::valueOf)
    //            .collect(Collectors.joining());
    //    System.out.println(streamOutput);

  }

  private void pipelineProductExample() {
    Sneaker sneaker = new Sneaker();
    sneaker.updatePrice(20);

    Pipeline<Product, Double> pipeline =
        new Pipeline<>(new ProductToDoubleFilter())
            .addPipe(new DiscountFilter(70))
            .addPipe(new TaxesFilter(0.15));

    System.out.println(pipeline.process(sneaker));
  }
}
