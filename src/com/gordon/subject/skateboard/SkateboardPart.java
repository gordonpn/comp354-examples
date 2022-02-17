package com.gordon.subject.skateboard;

import com.gordon.subject.Product;

import java.util.ArrayList;
import java.util.List;

public class SkateboardPart extends Product {
  List<SkateboardPart> parts;
  int value;

  public SkateboardPart(int value) {
    this.parts = new ArrayList<>();
    this.value = value;
  }

  public void addPart(SkateboardPart part) {
    this.parts.add(part);
  }

  public int getValue() {
    return value;
  }

  public int getTotalValue() {
    return this.parts.stream().mapToInt(SkateboardPart::getTotalValue).sum() + this.value;
  }
}
