package pl.bratek20.algorithms.solution.main;

import pl.bratek20.algorithms.solution.executor.ExecutorMock;

public class MainImplTest extends MainApiTest {

    @Override
    protected Context createContext() {
        var executorMock = new ExecutorMock();

        return new Context(
            new MainImpl(executorMock.getValue()),
            executorMock
        );
    }

}
