package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleTest;

import java.util.List;

class ShikakuSolverTest extends PuzzleTest {

    @Override
    protected Puzzle createSolution() {
        return new ShikakuSolver();
    }

    @Override
    protected List<TestData> testData() {
        return List.of(
            new TestData("example",
                """
                    10 10
                    0 0 0 0 0 0 0 0 9 0
                    0 0 0 0 0 0 9 0 0 0
                    0 0 0 0 0 0 0 0 0 0
                    0 20 0 0 8 0 0 0 6 0
                    0 0 0 0 0 0 0 0 0 0
                    0 0 0 6 0 0 6 0 0 0
                    10 0 0 0 0 0 0 0 0 0
                    0 0 0 0 0 0 0 0 0 0
                    0 0 6 0 6 0 0 0 8 0
                    0 0 0 0 0 0 6 0 0 0
                    """,
                """
                    1
                    AAAABBBCCC
                    AAAABBBCCC
                    AAAABBBCCC
                    AAAADDDDEE
                    AAAADDDDEE
                    FFGGGHHHEE
                    FFGGGHHHII
                    FFJJKKLLII
                    FFJJKKLLII
                    FFJJKKLLII
                    """
            ),
            new TestData("more then one solution",
                """
                    2 4
                    0 4
                    0 0
                    4 0
                    0 0
                    0 0
                    """,
                """
                    2
                    AA
                    AA
                    BB
                    BB
                    """
            )
        );
    }
}
