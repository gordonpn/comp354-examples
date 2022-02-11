package com.gordon.observer;

import com.gordon.Message;

public class EmailObserver implements Observer {
  private final String email;

  public EmailObserver(String email) {
    this.email = email;
  }

  @Override
  public void update(Message message) {
    System.out.printf("\nSent an email to %s with message:\n\t%s", email, message.getMessage());
  }
}
