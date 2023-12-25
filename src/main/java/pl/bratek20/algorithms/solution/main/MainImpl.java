package pl.bratek20.algorithms.solution.main;

import lombok.RequiredArgsConstructor;
import pl.bratek20.algorithms.solution.executor.Executor;

@RequiredArgsConstructor
public class MainImpl implements MainApi {
    private final Executor executor;

    @Override
    public void compile(String puzzleName, boolean spyInput) {

    }

    @Override
    public void generate(String puzzleUrl) {

    }

    @Override
    public void execute(String puzzleName) {
        executor.execute(puzzleName);
    }

}
