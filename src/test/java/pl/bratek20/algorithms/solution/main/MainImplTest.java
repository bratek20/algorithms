package pl.bratek20.algorithms.solution.main;

import pl.bratek20.algorithms.solution.executor.ExecutorMock;
import pl.bratek20.algorithms.solution.generator.GeneratorMock;

public class MainImplTest extends MainApiTest {

    @Override
    protected Context createContext() {
        var executorMock = new ExecutorMock();
        var generatorMock = new GeneratorMock();

        return new Context(
            new MainImpl(
                executorMock.getValue(),
                generatorMock.getValue()
            ),
            executorMock,
            generatorMock
        );
    }

}
