package com.gordon.subject;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StockLevelTest {
  static StockLevel stockLevel;

  @BeforeAll
  static void setUp() {
    stockLevel = new StockLevel();
  }

  @Test
  @Order(1)
  void firstStockCountShouldBeZero() {
    assertEquals(0, stockLevel.getCurrentStock());
  }

  @Test
  @Order(2)
  void afterAddingStock() {
    stockLevel.add(10);
    assertEquals(10, stockLevel.getCurrentStock());
  }

  @Test
  @Order(3)
  void afterAddingAnotherStock() {
    stockLevel.add(20);
    assertEquals(10, stockLevel.getPreviousStock());
  }
}
