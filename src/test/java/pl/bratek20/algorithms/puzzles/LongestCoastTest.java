package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleTest;

import java.util.List;

class LongestCoastTest extends PuzzleTest {

    @Override
    protected Puzzle createSolution() {
        return new LongestCoast();
    }

    @Override
    protected List<TestData> testData() {
        return List.of(
            new TestData("example",
                """
                    3
                    ~~#
                    ~~#
                    ~~#
                    """,
                    "1 3"
                ),
            new TestData("no water",
                """
                    1
                    ###
                    """,
                    "1 0"
                )
        );
    }
}