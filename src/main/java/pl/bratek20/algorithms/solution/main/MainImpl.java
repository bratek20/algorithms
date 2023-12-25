package pl.bratek20.algorithms.solution.main;

import lombok.RequiredArgsConstructor;
import pl.bratek20.algorithms.solution.executor.Executor;
import pl.bratek20.algorithms.solution.generator.Generator;

@RequiredArgsConstructor
public class MainImpl implements MainApi {
    private final Executor executor;
    private final Generator generator;

    @Override
    public void compile(String puzzleName, boolean spyInput) {

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
