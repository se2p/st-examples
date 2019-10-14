package de.uni_passau.fim.se2.st_examples;

/**
 * Adapted from
 * http://users.csc.calpoly.edu/~djanzen/research/TDD08/cdesai/IntroducingJUnit/IntroducingJUnit.html
 */
public class DivideMultiply {
  private double currentVal;

  /**
   * Constructor to initialize our member variable.
   *
   * @param pValue The value we want to initialise
   */
  public DivideMultiply(final double pValue) {
    currentVal = pValue;
  }

  /**
   * Multiply {@code pValue} with our current value.
   *
   * @param pValue The value we want to multiply to our value
   */
  public void multiply(final double pValue) {
    currentVal *= pValue;
  }

  /**
   * Divide {@code pValue} from our current value.
   *
   * @param pValue The value we want to divide from our value
   */
  public void divide(final double pValue) {
    if (pValue == 0.0) {
      throw new ArithmeticException("Can't divide by zero!");
    }

    currentVal /= pValue;
  }

  /**
   * Get the current value.
   *
   * @return The current value
   */
  public double getCurrentVal() {
    return currentVal;
  }
}

