package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleTest;

import java.util.List;

class ShootEnemyAircraftTest extends PuzzleTest {

    @Override
    protected Puzzle createSolution() {
        return new ShootEnemyAircraft();
    }

    @Override
    protected List<TestData> testData() {
        return List.of(
            new TestData("example",
                """
                    6
                    ....................
                    .>..................
                    ...................<
                    ....................
                    ....................
                    _________^__________
                    """,
                """
                    WAIT
                    WAIT
                    WAIT
                    SHOOT
                    WAIT
                    WAIT
                    SHOOT
                    """
            )
        );
    }
}
