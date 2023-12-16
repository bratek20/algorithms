package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleTest;

import java.util.List;

class AtoZTest extends PuzzleTest {

    @Override
    protected Puzzle createSolution() {
        return new AtoZ();
    }

    @Override
    protected List<TestData> testData() {
        return List.of(
                new TestData("example",
                        """
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
                                """,
                        """
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
                                """
                )
        );
    }

}