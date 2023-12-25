package pl.bratek20.algorithms.solution.executor;

import lombok.Getter;

import static org.mockito.Mockito.*;

public class ExecutorMock {
    @Getter
    private final Executor value = mock(Executor.class);

    public void assertExecuteCalledOnce(String puzzleName) {
        verify(value, times(1)).execute(puzzleName);
    }
}
