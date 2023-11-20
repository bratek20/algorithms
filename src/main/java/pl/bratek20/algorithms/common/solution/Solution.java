package pl.bratek20.algorithms.common.solution;

import pl.bratek20.algorithms.common.input.Input;
import pl.bratek20.algorithms.common.output.Output;

public abstract class Solution {
    protected Input in;
    protected Output out;

    public void init(Input input, Output output) {
        this.in = input;
        this.out = output;
    }
    protected abstract void solve();
}
