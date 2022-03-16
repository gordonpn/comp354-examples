package com.gordon.observer;

import com.gordon.message.Message;

public interface Observer {
  void update(Message message);
}
