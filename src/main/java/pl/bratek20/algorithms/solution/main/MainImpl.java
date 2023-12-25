package pl.bratek20.algorithms.solution.main;

import lombok.RequiredArgsConstructor;
import pl.bratek20.algorithms.solution.compiler.CompileArgs;
import pl.bratek20.algorithms.solution.executor.Executor;
import pl.bratek20.algorithms.solution.generator.Generator;
import pl.bratek20.algorithms.solution.compiler.Compiler;

@RequiredArgsConstructor
public class MainImpl implements MainApi {
    private final Executor executor;
    private final Generator generator;
    private final Compiler compiler;

    @Override
    public void compile(String puzzleName, boolean spyInput) {
        compiler.compile(new CompileArgs.Builder()
            .puzzleName(puzzleName)
            .basePath("src/main/java/")
            .attachMain(true)
            .spyInput(spyInput)
            .compileImports("pl.bratek20.algorithms.common.puzzle.PuzzleSolver")
            .importWholePackage(true)
            .build()
        );
    }

    @Override
    public void generate(String puzzleUrl) {
        generator.generate(puzzleUrl);
    }

    @Override
    public void execute(String puzzleName) {
        executor.execute(puzzleName);
    }

}
