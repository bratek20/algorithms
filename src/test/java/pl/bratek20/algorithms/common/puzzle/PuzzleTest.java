package pl.bratek20.algorithms.common.puzzle;

import org.junit.jupiter.api.Test;
import pl.bratek20.algorithms.common.input.TestNextLineProvider;
import pl.bratek20.algorithms.common.output.TestOutputProducer;

import java.util.List;

public abstract class PuzzleTest {
    public record TestData(String name, String input, String output) {}

    protected abstract Puzzle createSolution();
    protected abstract List<TestData> testData();

    @Test
    void testSolution() {
        testData().forEach(this::testForData);
    }

    private void testForData(TestData testData) {
        System.out.println("Testing " + testData.name());

        //given
        var solution = createSolution();
        var input = new TestNextLineProvider();
        var output = new TestOutputProducer();

        solution.init(input, output);
        input.init(testData.input());

        //when
        solution.solve();

        //then
        output.assertEquals(testData.output());
    }
}