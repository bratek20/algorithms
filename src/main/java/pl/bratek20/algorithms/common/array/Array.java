package pl.bratek20.algorithms.common.array;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Array<T> {
    private final List<ArrayCell<T>> cells;

    public Array(int n, T defaultValue) {
        this.cells = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            cells.add(new ArrayCell<>(i, defaultValue));
        }
    }

    public T get(int i) {
        return cells.get(i).getValue();
    }

    public void set(int i, T val) {
        cells.get(i).setValue(val);
    }

    public void forEach(Consumer<ArrayCell<T>> consumer) {
        for (int i = 0; i < cells.size(); i++) {
            consumer.accept(cells.get(i));
        }
    }
}
