package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleTest;

import java.util.List;

class SudokuValidatorTest extends PuzzleTest {

    @Override
    protected Puzzle createSolution() {
        return new SudokuValidator();
    }

    @Override
    protected List<TestData> testData() {
        return List.of(
            new TestData("example",
                """
                    1 2 3 4 5 6 7 8 9
                    4 5 6 7 8 9 1 2 3
                    7 8 9 1 2 3 4 5 6
                    9 1 2 3 4 5 6 7 8
                    3 4 5 6 7 8 9 1 2
                    6 7 8 9 1 2 3 4 5
                    8 9 1 2 3 4 5 6 7
                    2 3 4 5 6 7 8 9 1
                    5 6 7 8 9 1 2 3 4
                    """,
                """
                   true
                    """
            )
        );
    }
}