package com.gordon.observer;

import com.gordon.Message;

public class SmsObserver implements Observer {
  private final String phoneNumber;

  public SmsObserver(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public void update(Message message) {
    System.out.printf("%nSent SMS to %s with message: %n\t%s", phoneNumber, message.generateMessage());
  }
}
