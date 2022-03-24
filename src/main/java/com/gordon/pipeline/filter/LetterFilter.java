package com.gordon.pipeline.filter;

/** Removes the letters from a string and returns a string */
public class LetterFilter implements Filter<String, String> {
  @Override
  public String process(String input) {
    return input.replaceAll("[a-zA-Z]*", "");
  }
}
