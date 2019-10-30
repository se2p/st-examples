package de.uni_passau.fim.se2.counter;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.objectweb.asm.Opcodes.ASM7;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.Label;

class CounterMethodVisitorTest {

  private CounterClassVisitor ccv;
  private CounterMethodVisitor cmv;

  @BeforeEach
  void setup() {
    ccv = new CounterClassVisitor(ASM7);
    cmv = new CounterMethodVisitor(ASM7, null, ccv, "foo", "I:I");
  }

  @Test
  void test_visitLineNumber() throws Exception {
    cmv.visitLineNumber(42, mock(Label.class));

    final Field lineCountField = cmv.getClass().getDeclaredField("lineCount");
    lineCountField.setAccessible(true);
    final int lineCount = (int) lineCountField.get(cmv);

    final Field lineNumbersField = cmv.getClass().getDeclaredField("lineNumbers");
    lineNumbersField.setAccessible(true);
    @SuppressWarnings("unchecked")
    final Set<Integer> lineNumbers = (Set<Integer>) lineNumbersField.get(cmv);

    assertAll(
        () -> assertEquals(1, lineCount),
        () -> assertEquals(42, lineNumbers.iterator().next())
    );
  }

  @Test
  void test_visitEnd() {
    final Set<Integer> lineNumbers = new LinkedHashSet<>();
    lineNumbers.add(42);

    cmv.visitLineNumber(42, mock(Label.class));
    cmv.visitEnd();

    assertAll(
        () -> assertEquals(1, ccv.getLinesPerMethod().get("foo:I:I")),
        () -> assertEquals(lineNumbers, ccv.getLineNumbersPerMethod().get("foo:I:I"))
    );
  }

}