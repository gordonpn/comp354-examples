package com.gordon.singleton;

import com.gordon.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DaoSingletonFactoryTest {

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  void getInstanceWithSameName() {
    ProductRepository p1 = DaoSingletonFactory.getInstance("products");
    ProductRepository p2 = DaoSingletonFactory.getInstance("products");
    assertEquals(p1, p2);
  }

  @Test
  void getInstanceWithDiffName() {
    ProductRepository p1 = DaoSingletonFactory.getInstance("products");
    ProductRepository p2 = DaoSingletonFactory.getInstance("shoes");
    assertNotEquals(p1, p2);
  }
}
