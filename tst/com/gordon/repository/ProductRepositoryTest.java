package com.gordon.repository;

import com.gordon.subject.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductRepositoryTest {
  ProductRepository productRepository;

  @BeforeEach
  void setUp() {
    productRepository = mock(ProductRepository.class);
  }

  @AfterEach
  void tearDown() {}

  @Test
  void getByName() {
    Product product = new Product();
    given(productRepository.getByName("adidas")).willReturn(product);

    assertEquals(product, productRepository.getByName("adidas"));
  }

  @Test
  void getByNameMultiple() {
    Product product = new Product();
    Product product2 = new Product();

    when(productRepository.getByName(anyString())).thenReturn(product).thenReturn(product2);

    assertEquals(product, productRepository.getByName("adidas"));
    assertEquals(product2, productRepository.getByName("adidas"));
  }
}
