package de.uni_passau.fim.se2.counter;

import com.google.errorprone.annotations.Var;
import java.util.LinkedHashSet;
import java.util.Set;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class CounterMethodVisitor extends MethodVisitor {

  @Var private int lineCount;
  private final Set<Integer> lineNumbers;
  private final CounterClassVisitor ccv;
  private final String methodName;

  CounterMethodVisitor(
      final int pAPI,
      final MethodVisitor pMethodVisitor,
      final CounterClassVisitor pCCV,
      final String pMethodName,
      final String pDescriptor) {
    super(pAPI, pMethodVisitor);
    lineCount = 0;
    lineNumbers = new LinkedHashSet<>();
    ccv = pCCV;
    methodName = String.format("%s:%s", pMethodName, pDescriptor);
  }

  @Override
  public void visitLineNumber(final int pLineNumber, final Label pStart) {
    super.visitLineNumber(pLineNumber, pStart);
    lineCount++;
    lineNumbers.add(pLineNumber);
  }

  @Override
  public void visitEnd() {
    super.visitEnd();
    ccv.setLinesPerMethod(methodName, lineCount);
    ccv.setLineNumbersPerMethod(methodName, lineNumbers);
  }
}
