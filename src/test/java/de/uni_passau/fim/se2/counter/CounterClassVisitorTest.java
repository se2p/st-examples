package de.uni_passau.fim.se2.counter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ASM7;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.MethodVisitor;

class CounterClassVisitorTest {

  private CounterClassVisitor ccv;

  @BeforeEach
  void setup() {
    ccv = new CounterClassVisitor(ASM7);
  }

  @Test
  void test_linesPerMethod() {
    final Map<String, Integer> expected = new HashMap<>();
    expected.put("foo", 42);
    ccv.setLinesPerMethod("foo", 42);

    final Map<String, Integer> result = ccv.getLinesPerMethod();

    assertEquals(expected, result);
  }

  @Test
  void test_lineNumbersPerMethod() {
    final Map<String, Set<Integer>> expected = new HashMap<>();
    final Set<Integer> expectedLines = new LinkedHashSet<>();
    expectedLines.add(42);
    expected.put("foo", expectedLines);
    ccv.setLineNumbersPerMethod("foo", expectedLines);

    final Map<String, Set<Integer>> result = ccv.getLineNumbersPerMethod();

    assertEquals(expected, result);
  }

  @Test
  void test_visitMethod() {
    final MethodVisitor result = ccv.visitMethod(ACC_PUBLIC, "foo", "I:I", null, new String[] {});
    assertTrue(result instanceof CounterMethodVisitor);
  }
}
