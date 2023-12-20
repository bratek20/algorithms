package pl.bratek20.algorithms.puzzles;

import java.util.List;
import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleTest;

class TileAFloorTest extends PuzzleTest {

    @Override
    protected Puzzle createSolution() {
        return new TileAFloor();
    }

    @Override
    protected List<TestData> testData() {
        return List.of(
            new TestData("example",
                """
                    3
                    o  \s
                      O
                      *
                    """,
                """
                    +-----+-----+
                    |o   o|o   o|
                    |  O  |  O  |
                    |  *  |  *  |
                    |  O  |  O  |
                    |o   o|o   o|
                    +-----+-----+
                    |o   o|o   o|
                    |  O  |  O  |
                    |  *  |  *  |
                    |  O  |  O  |
                    |o   o|o   o|
                    +-----+-----+
                    """
            )
        );
    }
}