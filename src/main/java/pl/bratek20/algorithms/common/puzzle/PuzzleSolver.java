package pl.bratek20.algorithms.common.puzzle;

import pl.bratek20.algorithms.common.input.SystemNextLineProvider;
import pl.bratek20.algorithms.common.output.SystemOutputProducer;

public class PuzzleSolver {
    public void solve(Puzzle puzzle) {
        puzzle.init(new SystemNextLineProvider(), new SystemOutputProducer());
        puzzle.solve();
    }
}
