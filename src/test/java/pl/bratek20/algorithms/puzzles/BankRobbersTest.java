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
            ),
            new TestData("big heist",
                """
                    5
                    20
                    6 3
                    7 1
                    4 4
                    8 4
                    3 0
                    4 3
                    8 1
                    3 3
                    5 5
                    7 6
                    6 2
                    5 3
                    5 4
                    7 0
                    7 0
                    8 4
                    6 0
                    6 5
                    3 2
                    4 2
                    """,
                """
                    6515625
                    """
            )
        );
    }
}
