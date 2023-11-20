package pl.bratek20.algorithms.common.solution;

import org.junit.jupiter.api.Test;
import pl.bratek20.algorithms.common.input.TestInput;
import pl.bratek20.algorithms.common.output.TestOutput;

public abstract class SolutionTest {
    protected abstract Solution createSolution();
    protected abstract String testInput();
    protected abstract String testOutput();

    @Test
    void testSolution() {
       //given
       var solution = createSolution();
       var input = new TestInput();
       var output = new TestOutput();

       solution.init(input, output);
       input.init(testInput());

       //when
       solution.solve();

       //then
       output.assertEquals(testOutput());
    }
}