package pl.bratek20.algorithms.common.puzzle;

import pl.bratek20.algorithms.common.input.Input;
import pl.bratek20.algorithms.common.input.NextLineProvider;
import pl.bratek20.algorithms.common.output.Output;
import pl.bratek20.algorithms.common.output.OutputProducer;

public abstract class Puzzle {
    protected Input in;
    protected Output out;

    public void init(NextLineProvider nextLineProvider, OutputProducer outputProducer) {
        this.in = new Input(nextLineProvider);
        this.out = new Output(outputProducer);
    }
    public abstract void solve();
}
