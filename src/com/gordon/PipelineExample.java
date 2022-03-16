package com.gordon;

import com.gordon.pipeline.Pipeline;
import com.gordon.pipeline.filter.DiscountFilter;
import com.gordon.pipeline.filter.LetterFilter;
import com.gordon.pipeline.filter.ProductToDoubleFilter;
import com.gordon.pipeline.filter.SpaceFilter;
import com.gordon.pipeline.filter.TaxesFilter;
import com.gordon.subject.Product;
import com.gordon.subject.Sneaker;

import java.util.stream.Collectors;

public class PipelineExample {
  public static void main(String[] args) {
    String input = "1234 asdf 1234";
    Pipeline<String, String> pipeline = new Pipeline<>(new SpaceFilter());
    pipeline = pipeline.addPipe(new LetterFilter());

    String output = pipeline.process(input);

    System.out.println(output);

    // In Java, the same can be done with the built-in String libraries
    System.out.println(input.replaceAll("\\s", "").replaceAll("[a-zA-Z]*", ""));

    //  Or the Stream libraries
    String streamOutput =
        input
            .chars()
            .filter(Character::isDigit)
            .mapToObj(val -> (char) val)
            .map(String::valueOf)
            .collect(Collectors.joining());
    System.out.println(streamOutput);

    // An example with a domain-specific object
    Sneaker sneaker = new Sneaker();
    sneaker.updatePrice(20);

    Pipeline<Product, Double> productPipeline =
        new Pipeline<>(new ProductToDoubleFilter())
            .addPipe(new DiscountFilter(70))
            .addPipe(new TaxesFilter(0.15));

    System.out.println(productPipeline.process(sneaker));
  }
}
