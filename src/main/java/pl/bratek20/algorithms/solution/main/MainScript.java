package pl.bratek20.algorithms.solution.main;

import lombok.RequiredArgsConstructor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import pl.bratek20.algorithms.common.puzzle.PuzzleSolver;
import pl.bratek20.algorithms.solution.clipboard.Clipboard;
import pl.bratek20.algorithms.solution.compiler.Compiler;
import pl.bratek20.algorithms.solution.executor.Executor;
import pl.bratek20.algorithms.solution.generator.Generator;
import pl.bratek20.commons.script.CreateArgsException;
import pl.bratek20.commons.script.Script;

public class MainScript extends Script<MainApi, MainScript.Args> {

    protected MainScript(MainApi mainApi) {
        super(mainApi);
    }

    public interface Args {

    }
    record CompileArgs(String puzzleName, boolean spyInput) implements Args { }
    record GenerateArgs(String puzzleUrl) implements Args { }
    record ExecuteArgs(String puzzleName) implements Args { }

    @Override
    protected void addOptions(Options options) {
        options.addOption("c", "compile", false, "Compile command");
        options.addOption("g", "generate", false, "Generate command");
        options.addOption("e", "execute", false, "Execute command");

        options.addOption("pn", "puzzleName", true, "Puzzle name");
        options.addOption("pu", "puzzleUrl", true, "Puzzle url");

        options.addOption("spy", "spyInput", false, "Spy input (only used by compile)");
    }

    @Override
    protected Args createArgs(CommandLine commandLine) throws CreateArgsException {
        if (commandLine.hasOption("c")) {
            var puzzleName = commandLine.getOptionValue("pn");
            var spyInput = commandLine.hasOption("spy");
            return new CompileArgs(puzzleName, spyInput);
        } else if (commandLine.hasOption("g")) {
            var puzzleUrl = commandLine.getOptionValue("pu");
            return new GenerateArgs(puzzleUrl);
        } else if (commandLine.hasOption("e")) {
            var puzzleName = commandLine.getOptionValue("pn");
            return new ExecuteArgs(puzzleName);
        } else {
            throw new CreateArgsException("Command not found");
        }
    }

    @Override
    protected String run(MainApi api, Args args) {
        if (args instanceof CompileArgs compileArgs) {
            api.compile(compileArgs.puzzleName(), compileArgs.spyInput());
            return "Compiled puzzle " + compileArgs.puzzleName() + " copied to clipboard. Spy input: " + compileArgs.spyInput() + ".";
        } else if (args instanceof GenerateArgs generateArgs) {
            api.generate(generateArgs.puzzleUrl());
            return "Generated puzzle " + generateArgs.puzzleUrl() + ".";
        } else if (args instanceof ExecuteArgs executeArgs) {
            api.execute(executeArgs.puzzleName());
            return "Executed puzzle " + executeArgs.puzzleName() + ".";
        }
        return "Command not found";
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
