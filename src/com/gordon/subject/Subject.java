package com.gordon.subject;

import com.gordon.Message;
import com.gordon.observer.Observer;

public interface Subject {
  void subscribe(Observer observer);

  void unsubscribe(Observer observer);

  void notifyObservers(Message message);
}
