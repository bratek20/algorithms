package pl.bratek20.algorithms.solution.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.bratek20.algorithms.solution.executor.ExecutorMock;

abstract class MainApiTest {
    record Context(
        MainApi api,
        ExecutorMock executorMock
    ) {}

    protected abstract Context createContext();
    private Context c;

    @BeforeEach
    void setUp() {
        c = createContext();
    }

    @Test
    void shouldCompile() {

    }

    @Test
    void shouldGenerate() {

    }

    @Test
    void shouldExecute() {
        c.api().execute("puzzleName");

        c.executorMock().assertExecuteCalledOnce("puzzleName");
    }
}