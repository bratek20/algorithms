package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleTest;

import java.util.List;

class ReverseMinesweeperTest extends PuzzleTest {

    @Override
    protected Puzzle createSolution() {
        return new ReverseMinesweeper();
    }

    @Override
    protected List<TestData> testData() {
        return List.of(
            new TestData("example1",
                """
                    16
                    9
                    ................
                    ................
                    ................
                    ................
                    ................
                    ....x...........
                    ................
                    ................
                    ................
                    """,
                """
                    ................
                    ................
                    ................
                    ................
                    ...111..........
                    ...1.1..........
                    ...111..........
                    ................
                    ................
                    """
            )
        );
    }
}