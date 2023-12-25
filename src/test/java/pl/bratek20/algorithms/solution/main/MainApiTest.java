package pl.bratek20.algorithms.solution.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.bratek20.algorithms.solution.clipboard.ClipboardMock;
import pl.bratek20.algorithms.solution.compiler.CompileArgs;
import pl.bratek20.algorithms.solution.compiler.CompilerMock;
import pl.bratek20.algorithms.solution.executor.ExecutorMock;
import pl.bratek20.algorithms.solution.generator.GeneratorMock;

abstract class MainApiTest {
    record Context(
        MainApi api,
        ExecutorMock executorMock,
        GeneratorMock generatorMock,
        CompilerMock compilerMock,
        ClipboardMock clipboardMock
    ) {}

    protected abstract Context createContext();
    private Context c;

    @BeforeEach
    void setUp() {
        c = createContext();
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void shouldCompileAndCopyToClipboard(boolean spyInput) {
        String COMPILED_PUZZLE = "compiled puzzle";

        c.compilerMock.setCompileResult(COMPILED_PUZZLE);

        c.api().compile("puzzleName", spyInput);

        c.compilerMock().assertCompileCalledOnce(new CompileArgs.Builder()
            .puzzleName("puzzleName")
            .basePath("src/main/java/")
            .attachMain(true)
            .spyInput(spyInput)
            .compileImports("pl.bratek20.algorithms.common.puzzle.PuzzleSolver")
            .importWholePackage(true)
            .build()
        );

        c.clipboardMock().assertCopyCalledOnce(COMPILED_PUZZLE);
    }

    @Test
    void shouldGenerate() {
        c.api().generate("puzzleUrl");

        c.generatorMock().assertGenerateCalledOnce("puzzleUrl");
    }

    @Test
    void shouldExecute() {
        c.api().execute("puzzleName");

        c.executorMock().assertExecuteCalledOnce("puzzleName");
    }
}