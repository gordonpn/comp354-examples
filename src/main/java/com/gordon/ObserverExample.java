package com.gordon;

import com.gordon.observer.EmailObserver;
import com.gordon.observer.SmsObserver;
import com.gordon.subject.Sneaker;

public class ObserverExample {
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
  }
}
