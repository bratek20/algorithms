package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleTest;

import java.util.List;

class MovesInMazeTest extends PuzzleTest {

    @Override
    protected Puzzle createSolution() {
        return new MovesInMaze();
    }

    @Override
    protected List<TestData> testData() {
        return List.of(
            basic(),
            unreachable(),
            noWalls()
        );
    }

    private TestData basic() {
        return new TestData(
            """
                    10 5
                    ##########
                    #S.......#
                    ##.#####.#
                    ##.#.....#
                    ##########
                    """,
            """
                    ##########
                    #01234567#
                    ##2#####8#
                    ##3#DCBA9#
                    ##########
                    """
        );
    }

    private TestData unreachable() {
        return new TestData(
            """
                    5 3
                    #####
                    #S#.#
                    #####
                    """,
            """
                    #####
                    #0#.#
                    #####
                    """
        );
    }

    private TestData noWalls() {
        return new TestData(
            """
                    3 3
                    ...
                    .S.
                    ...
                    """,
            """
                    212
                    101
                    212
                    """
        );
    }
}