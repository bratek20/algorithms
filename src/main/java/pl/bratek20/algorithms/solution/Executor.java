package pl.bratek20.algorithms.solution;

import pl.bratek20.algorithms.common.input.SystemInput;
import pl.bratek20.algorithms.common.output.SystemOutput;
import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.puzzles.RetroTypewriterArt;

public class Executor {
    private static Puzzle createPuzzle() {
        return new RetroTypewriterArt();
    }

    public static void main(String[] args) {
        var puzzle = createPuzzle();
        puzzle.init(new SystemInput(), new SystemOutput());
        puzzle.solve();
    }
}
