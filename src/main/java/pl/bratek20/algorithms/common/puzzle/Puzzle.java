package pl.bratek20.algorithms.common.puzzle;

import pl.bratek20.algorithms.common.input.Input;
import pl.bratek20.algorithms.common.output.Output;

public abstract class Puzzle {
    protected Input in;
    protected Output out;

    public void init(Input input, Output output) {
        this.in = input;
        this.out = output;
    }
    public abstract void solve();
}
