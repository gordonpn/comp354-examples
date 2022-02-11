package com.gordon.observer;

import com.gordon.Message;

public interface Observer {
  void update(Message message);
}
