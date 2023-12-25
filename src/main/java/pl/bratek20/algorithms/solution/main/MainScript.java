package pl.bratek20.algorithms.solution.main;

import lombok.RequiredArgsConstructor;
import pl.bratek20.algorithms.common.puzzle.PuzzleSolver;
import pl.bratek20.algorithms.solution.clipboard.Clipboard;
import pl.bratek20.algorithms.solution.compiler.Compiler;
import pl.bratek20.algorithms.solution.executor.Executor;
import pl.bratek20.algorithms.solution.generator.Generator;

@RequiredArgsConstructor
public class MainScript {
    private final MainApi api;

    public void run(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: <command> <puzzleName>");
            return;
        }

        var command = args[0];
        var puzzleName = args[1];
        System.out.printf("Command: [%s], puzzle name [%s]\n", command, puzzleName);

        if (command.equals("-c")) {
            var spyInput = args.length > 2 && args[2].equals("-spy");
            compile(puzzleName, spyInput);
        } else if (command.equals("-g")) {
            generate(puzzleName);
        } else if (command.equals("-e")) {
            execute(puzzleName);
        } else {
            System.out.printf("Command %s not found. Commands: -c, -g, -e\n", command);
        }
    }

    private void compile(String puzzleName, boolean spyInput) {
        api.compile(puzzleName, spyInput);
        System.out.println("Compiled puzzle " + puzzleName + " copied to clipboard. Spy input: " + spyInput + ".");
    }

    private void generate(String puzzleName) {
        api.generate(puzzleName);
        System.out.println("Generated puzzle " + puzzleName + ".");
    }

    private void execute(String puzzleName) {
        api.execute(puzzleName);
        System.out.println("Executed puzzle " + puzzleName + ".");
    }

    public static void main(String[] args) {
        var api = new MainImpl(
            new Executor("pl.bratek20.algorithms.puzzles", new PuzzleSolver()),
            new Generator("src/main/java/", "src/test/java/"),
            new Compiler(),
            new Clipboard()
        );

        new MainScript(api).run(args);
    }
}
