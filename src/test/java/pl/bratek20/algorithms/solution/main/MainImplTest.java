package pl.bratek20.algorithms.solution.main;

import pl.bratek20.algorithms.solution.compiler.CompilerMock;
import pl.bratek20.algorithms.solution.executor.ExecutorMock;
import pl.bratek20.algorithms.solution.generator.GeneratorMock;

public class MainImplTest extends MainApiTest {

    @Override
    protected Context createContext() {
        var executorMock = new ExecutorMock();
        var generatorMock = new GeneratorMock();
        var compilerMock = new CompilerMock();

        return new Context(
            new MainImpl(
                executorMock.getValue(),
                generatorMock.getValue(),
                compilerMock.getValue()
            ),
            executorMock,
            generatorMock,
            compilerMock
        );
    }

}
