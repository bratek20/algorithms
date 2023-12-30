package pl.bratek20.algorithms.common.utils;

public class IntVariable extends Variable<Integer> {

    public IntVariable(Integer value) {
        super(value);
    }

    public IntVariable() {
        super(0);
    }

    public void increment() {
        set(get() + 1);
    }
}
