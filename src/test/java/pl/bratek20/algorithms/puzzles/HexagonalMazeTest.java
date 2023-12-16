package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleTest;

import java.util.List;

class HexagonalMazeTest extends PuzzleTest {

    @Override
    protected Puzzle createSolution() {
        return new HexagonalMaze();
    }

    @Override
    protected List<TestData> testData() {
        return List.of(
                new TestData("example",
                        """
                                5 6
                                #####
                                #S#E#
                                #_#_#
                                #_#_#
                                #___#
                                #####
                                """,
                        """
                                #####
                                #S#E#
                                #.#.#
                                #.#.#
                                #_..#
                                #####
                                """
                )
        );
    }
}