package pl.bratek20.algorithms.common.puzzle;

import pl.bratek20.algorithms.common.input.Input;
import pl.bratek20.algorithms.common.input.NextLineProvider;
import pl.bratek20.algorithms.common.output.Output;

public abstract class Puzzle {
    protected Input in;
    protected Output out;

    public void init(NextLineProvider nextLineProvider, Output output) {
        this.in = new Input(nextLineProvider);
        this.out = output;
    }
    public abstract void solve();
}
