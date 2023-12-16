package pl.bratek20.algorithms.common.utils;

public class Variable<T> {
    private T value;

    public Variable(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void set(T newValue) {
        value = newValue;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
