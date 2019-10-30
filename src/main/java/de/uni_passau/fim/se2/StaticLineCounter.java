package de.uni_passau.fim.se2;

import static org.objectweb.asm.Opcodes.ASM7;

import de.uni_passau.fim.se2.counter.CounterClassVisitor;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.objectweb.asm.ClassReader;

public class StaticLineCounter {

  private StaticLineCounter() {}

  private void extractInformation(final String pClassName) throws IOException {
    final ClassReader cr = new ClassReader(pClassName);
    final CounterClassVisitor ccv = new CounterClassVisitor(ASM7);
    cr.accept(ccv, ClassReader.EXPAND_FRAMES);

    final Map<String, Integer> linesPerMethod = ccv.getLinesPerMethod();
    final Map<String, Set<Integer>> lineNumbersPerMethod = ccv.getLineNumbersPerMethod();

    for (Entry<String, Integer> method : linesPerMethod.entrySet()) {
      System.out.printf("Information for method %s\n", method.getKey());
      System.out.printf("    Line count: %d\n", method.getValue());
      System.out.printf(
          "    Line numbers in method: %s\n\n", lineNumbersPerMethod.get(method.getKey()));
    }
  }

  private String parseArguments(final String[] pArgs) {
    final Options options = new Options();

    final Option classNameOption = new Option("c", "className", true, "Full-qualified name of class to analyse");
    classNameOption.setRequired(true);
    options.addOption(classNameOption);

    final CommandLineParser parser = new DefaultParser();
    final HelpFormatter helpFormatter = new HelpFormatter();
    final CommandLine commandLine;
    try {
      commandLine = parser.parse(options, pArgs);
      return commandLine.getOptionValue("className");
    } catch (final ParseException pE) {
      System.err.println(pE.getMessage());
      helpFormatter.printHelp("StaticLineCounter", options);
      return null;
    }
  }

  public static void main(final String[] pArgs) throws IOException {
    final StaticLineCounter counter = new StaticLineCounter();
    final String className = counter.parseArguments(pArgs);
    counter.extractInformation(className);
  }
}
