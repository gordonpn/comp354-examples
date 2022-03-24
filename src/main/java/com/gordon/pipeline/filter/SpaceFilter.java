package com.gordon.pipeline.filter;

/** Removes whitespaces from a string and returns a string */
public class SpaceFilter implements Filter<String, String> {
  @Override
  public String process(String input) {
    return input.replaceAll("\\s", "");
  }
}
