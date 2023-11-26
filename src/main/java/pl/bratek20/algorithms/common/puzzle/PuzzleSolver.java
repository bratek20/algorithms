package pl.bratek20.algorithms.common.puzzle;

import pl.bratek20.algorithms.common.input.SystemInput;
import pl.bratek20.algorithms.common.output.SystemOutput;

public class PuzzleSolver {
    public void solve(Puzzle puzzle) {
        puzzle.init(new SystemInput(), new SystemOutput());
        puzzle.solve();
    }
}
