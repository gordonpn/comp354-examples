package com.gordon;

import com.gordon.subject.skateboard.Deck;
import com.gordon.subject.skateboard.Trucks;
import com.gordon.subject.skateboard.Wheels;

public class CompositeExample {
  public static void main(String[] args) {
    Wheels wheels = new Wheels(10);
    Trucks trucks = new Trucks(20);
    trucks.addPart(wheels);
    trucks.addPart(wheels);

    Deck deck = new Deck(50);
    deck.addPart(trucks);
    deck.addPart(trucks);

    return deck;
  }
}
