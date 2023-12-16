package pl.bratek20.algorithms.common.array;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Array<T> {
    private final List<T> value;

    public Array(int n, T defaultValue) {
        this.value = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            value.add(defaultValue);
        }
    }

    public T get(int i) {
        return value.get(i);
    }

    public void set(int i, T val) {
        value.set(i, val);
    }

    public void forEach(BiConsumer<T, Integer> consumer) {
        for (int i = 0; i < value.size(); i++) {
            consumer.accept(value.get(i), i);
        }
    }
}
