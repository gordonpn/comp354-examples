package com.gordon.service.external;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntlSalesTaxServiceTest {
  IntlSalesTaxService service;

  public static List<String> europeanCountries() {
    return new ArrayList<>(Arrays.asList("sweden", "norway", "denmark"));
  }

  @BeforeEach
  void setUp() {
    service = new IntlSalesTaxService();
  }

  @AfterEach
  void tearDown() {}

  @Test
  void getSalesTaxFor() {
    double taxRate = service.getSalesTaxFor("sweden");
    assertEquals(25.0, taxRate, 0.01);
  }

  @ParameterizedTest
  @MethodSource(value = "europeanCountries")
  void getSalesTaxForAll(String country) {
    double taxRate = service.getSalesTaxFor(country);
    assertEquals(25.0, taxRate, 0.01);
  }

  @Test
  void getSalesTaxForUnsupported() {
    UnsupportedOperationException thrown =
        assertThrows(
            UnsupportedOperationException.class,
            () -> {
              double taxRate = service.getSalesTaxFor("mexico");
            });

    assertEquals("mexico not supported", thrown.getMessage());
  }
}
