package de.uni_passau.fim.se2.st_examples;

import static org.objectweb.asm.Opcodes.ASM7;

import java.io.IOException;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public class ClassPrinter extends ClassVisitor {

  private ClassPrinter() {
    super(ASM7);
  }

  @Override
  public void visit(int version, int access, String name, String signature, String superName,
      String[] interfaces) {
    System.out.println(name + " extends " + superName + " {");
  }

  @Override
  public void visitEnd() {
    System.out.println("}");
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
      String[] exceptions) {
    System.out.println("    " + name + descriptor);
    return null;
  }

  @Override
  public FieldVisitor visitField(int access, String name, String descriptor, String signature,
      Object value) {
    System.out.println("    " + descriptor + " " + name);
    return null;
  }

  public static void main(String[] args) throws IOException {
    final ClassPrinter cp = new ClassPrinter();
    final ClassReader cr = new ClassReader("java.lang.String");
    cr.accept(cp, 0);
  }

}
