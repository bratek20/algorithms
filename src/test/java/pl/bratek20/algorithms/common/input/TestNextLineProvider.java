package pl.bratek20.algorithms.common.input;

import java.util.List;

public class TestNextLineProvider implements NextLineProvider {
    private List<String> lines;
    private int index = 0;

    @Override
    public String readLine() {
        return lines.get(index++);
    }

    public void init(String s) {
        this.lines = List.of(s.split("\n"));
    }
}