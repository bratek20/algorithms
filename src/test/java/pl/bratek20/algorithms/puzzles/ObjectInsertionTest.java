package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleTest;

import java.util.List;

class ObjectInsertionTest extends PuzzleTest {

    @Override
    protected Puzzle createSolution() {
        return new ObjectInsertion();
    }

    @Override
    protected List<TestData> testData() {
        return List.of(
            new TestData("example",
                """
                    3 2
                    .*
                    **
                    .*
                    8 10
                    #..#######
                    #.##..####
                    ###..##...
                    ####.#####
                    ##.#######
                    ##......##
                    ##.....###
                    ########..
                    """,
                """
                    1
                    #..#######
                    #.##*.####
                    ###**##...
                    ####*#####
                    ##.#######
                    ##......##
                    ##.....###
                    ########..
                    """
            )
        );
    }
}
