package de.uni_passau.fim.se2.st_examples;

/**
 * Adapted from
 * http://users.csc.calpoly.edu/~djanzen/research/TDD08/cdesai/IntroducingJUnit/IntroducingJUnit.html
 */
public class AddSubtract {
  private int currentVal;

  /**
   * Constructor to initialize our member variable.
   *
   * @param pValue The base value of initialisation
   */
  public AddSubtract(final int pValue) {
    currentVal = pValue;
  }

  /**
   * Add the value of {@code pValue} to our current value.
   *
   * @param pValue The value we want to add to our current value
   */
  public void add(final int pValue) {
    currentVal += pValue;
  }

  /**
   * Subtract the value of {@code pValue} from our current value.
   *
   * @param pValue The value we want to subtract from our current value
   */
  public void subtract(final int pValue) {
    currentVal -= pValue;
  }

  /**
   * Get the current value.
   *
   * @return The current value
   */
  public int getCurrentVal() {
    return currentVal;
  }
}