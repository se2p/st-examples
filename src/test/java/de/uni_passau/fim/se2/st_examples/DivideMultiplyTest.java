package de.uni_passau.fim.se2.st_examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DivideMultiplyTest {

  private DivideMultiply divideMultiply;

  @BeforeEach
  void setup() {
    divideMultiply = new DivideMultiply(4.0);
  }

  @Test
  void test_multiply() {
    divideMultiply.multiply(2.0);
    assertEquals(8.0, divideMultiply.getCurrentVal(), 1e-5);
  }

  @Test
  void test_divide() {
    divideMultiply.divide(2.0);
    assertEquals(2.0, divideMultiply.getCurrentVal(), 1e-5);
  }

  @Test
  void test_divide_exception() {
    Throwable t = assertThrows(ArithmeticException.class, () -> divideMultiply.divide(0));
    assertEquals("Can't divide by zero!", t.getMessage());
  }
}