package pl.bratek20.algorithms.solution.executor;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleSolver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

class ExecutorTest {
    @Test
    void shouldExecutePuzzle() {
        //given
        var puzzleSolver = mock(PuzzleSolver.class);
        var argsCaptor = ArgumentCaptor.forClass(Puzzle.class);
        doNothing().when(puzzleSolver).solve(argsCaptor.capture());

        var executor = new Executor("pl.bratek20.algorithms.solution.executor", puzzleSolver);

        //when
        executor.execute("SomePuzzle");

        //then
        var puzzle = argsCaptor.getValue();
        assertThat(puzzle).isInstanceOf(SomePuzzle.class);
    }
}