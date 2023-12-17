package pl.bratek20.algorithms.common.output;

import static org.assertj.core.api.Assertions.assertThat;

public class TestOutputProducer implements OutputProducer {
    private String expected = "";

    @Override
    public void print(char c) {
        expected += c;
    }

    @Override
    public void print(String s) {
        expected += s;
    }

    public void assertEquals(String s) {
        assertThat(expected).isEqualTo(s);
    }
}