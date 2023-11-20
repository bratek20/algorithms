package pl.bratek20.algorithms.common.input;

public class TestInput implements Input {
    private String line;

    @Override
    public String nextLine() {
        return line;
    }

    public void init(String s) {
        this.line = s;
    }
}