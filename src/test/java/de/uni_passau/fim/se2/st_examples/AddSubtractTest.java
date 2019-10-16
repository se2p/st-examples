package de.uni_passau.fim.se2.st_examples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddSubtractTest {

  private AddSubtract addSubtract;

  @BeforeEach
  void setup() {
    addSubtract = new AddSubtract(42);
  }

  @Test
  void test_add() {
    addSubtract.add(23);

    assertEquals(65, addSubtract.getCurrentVal());
  }

  @Test
  void test_sub() {
    addSubtract.subtract(21);

    assertEquals(21, addSubtract.getCurrentVal());
  }
}