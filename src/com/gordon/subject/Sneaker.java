package com.gordon.subject;

import com.gordon.Message;
import com.gordon.PriceMessage;
import com.gordon.StockMessage;
import com.gordon.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Sneaker extends Product implements Subject {
  List<Observer> observerList;

  public Sneaker() {
    super();
    this.observerList = new ArrayList<>();
  }

  @Override
  public void updateStock(int newStock) {
    super.updateStock(newStock);
    notifyObservers(new StockMessage(this.stockLevel));
  }

  @Override
  public void updatePrice(int newPrice) {
    super.updatePrice(newPrice);
    notifyObservers(new PriceMessage(this.price));
  }

  @Override
  public void subscribe(Observer observer) {
    observerList.add(observer);
  }

  @Override
  public void unsubscribe(Observer observer) {
    observerList.remove(observer);
  }

  @Override
  public void notifyObservers(Message message) {
    observerList.forEach(observer -> observer.update(message));
  }
}
