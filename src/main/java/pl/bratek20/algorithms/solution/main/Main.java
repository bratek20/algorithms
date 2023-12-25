package pl.bratek20.algorithms.solution.main;

import pl.bratek20.algorithms.common.puzzle.PuzzleSolver;
import pl.bratek20.algorithms.solution.compiler.Compiler;
import pl.bratek20.algorithms.solution.compiler.CompilerConfig;
import pl.bratek20.algorithms.solution.executor.Executor;
import pl.bratek20.algorithms.solution.generator.Generator;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: <command> <puzzleName>");
            return;
        }

        var command = args[0];
        var puzzleName = args[1];
        System.out.printf("Command: [%s], puzzle name [%s]\n", command, puzzleName);

        if (command.equals("-c")) {
            compile(puzzleName);
        } else if (command.equals("-g")) {
            generate(puzzleName);
        } else if (command.equals("-e")) {
            execute(puzzleName);
        } else {
            System.out.printf("Command %s not found. Commands: -c, -g, -e\n", command);
        }
    }

    private static void compile(String puzzleName) {
        var compiler = new Compiler(new CompilerConfig.Builder()
            .basePath("src/main/java/")
            .attachMain(true)
            .compileImports("pl.bratek20.algorithms.common.puzzle.PuzzleSolver")
            .importWholePackage(true)
            .build()
        );

        var puzzle = compiler.compile(puzzleName);

        copyToClipboard(puzzle);
        System.out.println("Compiled puzzle " + puzzleName + " copied to clipboard");
    }

    private static void copyToClipboard(String text) {
        var clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        var transferable = new StringSelection(text);
        clipboard.setContents(transferable, null);
    }

    private static void generate(String puzzleName) {
        var creator = new Generator("src/main/java/", "src/test/java/");
        creator.create(puzzleName);
    }

    private static void execute(String puzzleName) {
        var executor = new Executor("pl.bratek20.algorithms.puzzles", new PuzzleSolver());
        executor.execute(puzzleName);
    }
}
