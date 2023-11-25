package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleTest;

class RetroTypewriterArtTest extends PuzzleTest {

    @Override
    protected Puzzle createSolution() {
        return new RetroTypewriterArt();
    }

    @Override
    protected String testInput() {
        return
            "1sp 1/ 1bS 1_ 1/ 1bS nl 1( 1sp 1o 1. 1o 1sp 1) nl 1sp 1> 1sp 1^ 1sp 1< nl 2sp 3|";
    }

    @Override
    protected String testOutput() {
        return
            """
             /\\_/\\
            ( o.o )
             > ^ <
              |||""";
    }
}