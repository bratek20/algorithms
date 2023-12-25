package pl.bratek20.algorithms.solution.executor;

import lombok.Getter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ExecutorMock {
    @Getter
    private final Executor value = mock(Executor.class);

    public void assertExecuteCalledOnce(String puzzleName) {
        verify(value).execute(puzzleName);
    }
}
