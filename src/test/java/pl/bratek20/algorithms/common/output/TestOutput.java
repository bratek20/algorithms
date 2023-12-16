package pl.bratek20.algorithms.common.output;

import static org.assertj.core.api.Assertions.assertThat;

public class TestOutput implements Output {
    private String expected = "";

    @Override
    public void print(char c) {
        expected += c;
    }

    @Override
    public void print(String s) {
        expected += s;
    }

    @Override
    public void println(String s) {
        expected += s + "\n";
    }

    @Override
    public void println() {
        expected += "\n";
    }

    public void assertEquals(String s) {
        assertThat(expected).isEqualTo(s);
    }
}