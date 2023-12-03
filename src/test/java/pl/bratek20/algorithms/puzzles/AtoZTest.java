package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleTest;

class AtoZTest extends PuzzleTest {

    @Override
    protected Puzzle createSolution() {
        return new AtoZ();
    }

    @Override
    protected String testInput() {
        return """
            10
            qadnhwbnyw
            iiopcygydk
            bahlfiojdc
            cfijtdmkgf
            dzhkliplzg
            efgrmpqryx
            loehnovstw
            jrsacymeuv
            fpnocpdkrs
            jlmsvwvuih
            """;
    }

    @Override
    protected String testOutput() {
        return """
            ----------
            ----------
            ba--------
            c-ij------
            d-hkl---z-
            efg-mpqryx
            ----no-stw
            --------uv
            ----------
            ----------
            """;
    }

}