package com.gordon.subject;

import com.gordon.Message;
import com.gordon.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Sneaker implements Subject {
  List<Observer> observerList;
  List<Integer> stockHistory;

  public Sneaker() {
    this.observerList = new ArrayList<>();
    this.stockHistory = new ArrayList<>();
    this.stockHistory.add(0);
  }

  public void updateStock(int newStock) {
    this.stockHistory.add(newStock);
    notifyObservers();
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
  public void notifyObservers() {
    observerList.forEach(observer -> {
      Message message = new Message(String.format("New stock level: %s\n\tPrevious stock level: %s\n", stockHistory.get(stockHistory.size() - 1), stockHistory.get(stockHistory.size() - 2)));
      observer.update(message);
    });
  }
}
