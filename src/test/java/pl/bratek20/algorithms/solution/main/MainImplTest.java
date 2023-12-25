package pl.bratek20.algorithms.solution.main;

import pl.bratek20.algorithms.solution.compiler.CompilerMock;
import pl.bratek20.algorithms.solution.executor.ExecutorMock;
import pl.bratek20.algorithms.solution.generator.GeneratorMock;
import pl.bratek20.algorithms.solution.clipboard.ClipboardMock;

public class MainImplTest extends MainApiTest {

    @Override
    public Context createContext() {
        var executorMock = new ExecutorMock();
        var generatorMock = new GeneratorMock();
        var compilerMock = new CompilerMock();
        var clipboardMock = new ClipboardMock();

        return new Context(
            new MainImpl(
                executorMock.getValue(),
                generatorMock.getValue(),
                compilerMock.getValue(),
                clipboardMock.getValue()
            ),
            executorMock,
            generatorMock,
            compilerMock,
            clipboardMock
        );
    }
}
