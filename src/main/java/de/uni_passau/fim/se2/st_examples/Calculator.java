package de.uni_passau.fim.se2.st_examples;

import com.google.common.base.Splitter;
import com.google.errorprone.annotations.Var;

/**
 * Modified from JUnit 4 "Getting started" wiki page, cf.
 * https://github.com/junit-team/junit4/wiki/Getting-started
 */
public class Calculator {

  public int evaluate(final String pExpression) {
    @Var int sum = 0;
    for (final String summand : Splitter.on('+').split(pExpression)) {
      sum += Integer.parseInt(summand);
    }
    return sum;
  }
}