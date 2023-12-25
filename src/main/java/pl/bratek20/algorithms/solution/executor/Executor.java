package pl.bratek20.algorithms.solution.executor;

import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleSolver;
import pl.bratek20.algorithms.puzzles.RetroTypewriterArt;

public class Executor {
    private static Puzzle createPuzzle() {
        return new RetroTypewriterArt();
    }

    public static void main(String[] args) {
        new PuzzleSolver().solve(createPuzzle());
    }
}
