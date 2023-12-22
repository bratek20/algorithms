package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleTest;

import java.util.List;

class BankRobbersTest extends PuzzleTest {

    @Override
    protected Puzzle createSolution() {
        return new BankRobbers();
    }

    @Override
    protected List<TestData> testData() {
        return List.of(
            new TestData("example",
                """
                1
                1
                3 1
                """,
                """
                250
                """
            ),
            new TestData("harder example",
                """
                2
                3
                2 2
                1 1
                2 2
                """,
                """
                110
                """
            ),
            new TestData("more robbers than vaults",
                """
                2
                1
                1 1
                """,
                """
                10
                """
            )
        );
    }
}
