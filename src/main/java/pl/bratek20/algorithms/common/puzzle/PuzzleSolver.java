package pl.bratek20.algorithms.common.puzzle;

import pl.bratek20.algorithms.common.input.NextLineProvider;
import pl.bratek20.algorithms.common.input.SpyingNextLineProvider;
import pl.bratek20.algorithms.common.input.SystemNextLineProvider;
import pl.bratek20.algorithms.common.output.SystemOutputProducer;

public class PuzzleSolver {
    private final boolean spyInput;

    public PuzzleSolver() {
        this(false);
    }

    public PuzzleSolver(boolean spyInput) {
        this.spyInput = spyInput;
    }

    public void solve(Puzzle puzzle) {
        puzzle.init(createNextLineProvider(), new SystemOutputProducer());
        puzzle.solve();
    }

    private NextLineProvider createNextLineProvider() {
        var provider = new SystemNextLineProvider();
        if (spyInput) {
            return new SpyingNextLineProvider(provider);
        }
        return provider;
    }
}
