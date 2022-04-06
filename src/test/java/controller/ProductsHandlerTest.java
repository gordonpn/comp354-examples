package com.gordon.controller;

import com.gordon.repository.ProductRepository;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import static com.gordon.controller.ControllerHelper.logResponse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

class ProductsHandlerTest {
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  void mockHandlerStubRequest() throws IOException {
    ProductsHandler productsHandler = mock(ProductsHandler.class);
    HttpExchange stubExchange = new HttpExchangeStub();
    productsHandler.handle(stubExchange);
    verify(productsHandler, times(1)).handle(stubExchange);
  }

  @Test
  void mockRequest() {
    HttpExchange exchange = mock(HttpExchange.class);
    String strURI = "https://www.google.com";
    when(exchange.getRequestURI()).thenReturn(URI.create(strURI));
    when(exchange.getRequestMethod()).thenReturn("GET");

    assertEquals(strURI, exchange.getRequestURI().toString());
  }

  @Test
  void mockStaticWithSpy() throws IOException {
    HttpExchange exchange = mock(HttpExchange.class);
    String strURI = "https://www.google.com";
    when(exchange.getRequestURI()).thenReturn(URI.create(strURI));
    when(exchange.getRequestMethod()).thenReturn("GET");
    when(exchange.getResponseBody()).thenReturn(System.out);

    MockedStatic<ControllerHelper> controllerHelper = mockStatic(ControllerHelper.class);
    ProductsHandler productsHandler = spy(ProductsHandler.class);
    productsHandler.handle(exchange);
    controllerHelper.verify(() -> logResponse(exchange), times(1));
  }

  // @Disabled
  @Test
  void logOutputTest() {
    HttpExchange exchange = mock(HttpExchange.class);
    String strURI = "https://www.google.com";
    when(exchange.getRequestURI()).thenReturn(URI.create(strURI));
    when(exchange.getRequestMethod()).thenReturn("GET");

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    logResponse(exchange, byteArrayOutputStream);

    String result = byteArrayOutputStream.toString(StandardCharsets.UTF_8);
    result = result.substring(result.indexOf(" ") + 1);
    System.out.println("result: \"" + result + "\"");

    assertEquals("GET https://www.google.com null", result);
  }

  // @Disabled
  @Test
  void logOutputTestAlternative() {
    HttpExchange exchange = mock(HttpExchange.class);
    String strURI = "https://www.google.com";
    when(exchange.getRequestURI()).thenReturn(URI.create(strURI));
    when(exchange.getRequestMethod()).thenReturn("GET");

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    logResponse(exchange);

    String result = outContent.toString();
    result = result.substring(result.indexOf(" ") + 1);

    assertEquals("GET https://www.google.com null", result);
  }

  @Test
  void handlerSpyCalledRepository() throws IOException {
    HttpExchange exchange = mock(HttpExchange.class);
    String strURI = "https://www.google.com?name=nike";
    when(exchange.getRequestURI()).thenReturn(URI.create(strURI));
    when(exchange.getRequestMethod()).thenReturn("GET");
    when(exchange.getResponseBody()).thenReturn(System.out);

    ProductRepository productRepository = spy(ProductRepository.class);

    ProductsHandler productsHandler = mock(
        ProductsHandler.class,
        withSettings().useConstructor(productRepository).defaultAnswer(CALLS_REAL_METHODS));
    productsHandler.handle(exchange);

    verify(productsHandler, times(1)).handleOneProduct(exchange, "nike");
    // verify(productsHandler, never()).handleOneProduct(exchange, "nike");
    verify(productRepository, atMost(1)).getByName("nike");
    // verify(productRepository, never()).getByName("nike");
    verify(productRepository, never()).getAll();
  }

  @Test
  void captorExample() throws IOException {
    ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

    HttpExchange exchange = mock(HttpExchange.class);
    String strURI = "https://www.google.com?name=nike";
    when(exchange.getRequestURI()).thenReturn(URI.create(strURI));
    when(exchange.getRequestMethod()).thenReturn("GET");
    when(exchange.getResponseBody()).thenReturn(System.out);

    ProductRepository productRepository = mock(ProductRepository.class);

    ProductsHandler productsHandler = new ProductsHandler(productRepository);
    productsHandler.handle(exchange);

    then(productRepository).should().getByName(argumentCaptor.capture());
    assertThat(argumentCaptor.getValue(), is("nike"));
  }

  class HttpExchangeStub extends HttpExchange {

    @Override
    public Headers getRequestHeaders() {
      return null;
    }

    @Override
    public Headers getResponseHeaders() {
      return null;
    }

    @Override
    public URI getRequestURI() {
      return null;
    }

    @Override
    public String getRequestMethod() {
      return null;
    }

    @Override
    public HttpContext getHttpContext() {
      return null;
    }

    @Override
    public void close() {
    }

    @Override
    public InputStream getRequestBody() {
      return null;
    }

    @Override
    public OutputStream getResponseBody() {
      return null;
    }

    @Override
    public void sendResponseHeaders(int rCode, long responseLength) {
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
      return null;
    }

    @Override
    public int getResponseCode() {
      return 0;
    }

    @Override
    public InetSocketAddress getLocalAddress() {
      return null;
    }

    @Override
    public String getProtocol() {
      return null;
    }

    @Override
    public Object getAttribute(String name) {
      return null;
    }

    @Override
    public void setAttribute(String name, Object value) {
    }

    @Override
    public void setStreams(InputStream i, OutputStream o) {
    }

    @Override
    public HttpPrincipal getPrincipal() {
      return null;
    }
  }
}
