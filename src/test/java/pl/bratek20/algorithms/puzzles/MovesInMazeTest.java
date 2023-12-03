package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleTest;

class MovesInMazeTest extends PuzzleTest {

    @Override
    protected Puzzle createSolution() {
        return new MovesInMaze();
    }

    @Override
    protected String testInput() {
        return """
            10 5
            ##########
            #S.......#
            ##.#####.#
            ##.#.....#
            ##########
            """;
    }

    @Override
    protected String testOutput() {
        return """
            ##########
            #01234567#
            ##2#####8#
            ##3#DCBA9#
            ##########
            """;
    }
}