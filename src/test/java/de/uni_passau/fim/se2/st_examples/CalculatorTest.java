package de.uni_passau.fim.se2.st_examples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CalculatorTest {

  @Test
  void test() {
    Calculator calculator = new Calculator();

    int result = calculator.evaluate("1+2+3");

    assertEquals(6, result);
  }
}