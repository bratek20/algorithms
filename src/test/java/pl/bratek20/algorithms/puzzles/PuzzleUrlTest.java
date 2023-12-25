package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleTest;

import java.util.List;

class PuzzleUrlTest extends PuzzleTest {

    @Override
    protected Puzzle createSolution() {
        return new PuzzleUrl();
    }

    @Override
    protected List<TestData> testData() {
        return List.of(
            new TestData("example",
                """
                TODO input
                """,
                """
                TODO output
                """
            )
        );
    }
}
