package pl.bratek20.algorithms.common.array.d1;

import pl.bratek20.algorithms.common.array.AbstractArray;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Array<T> extends AbstractArray<T, Integer, ArrayCell<T>, Array<T>> {
    private final List<ArrayCell<T>> cells;

    public Array(int n, T defaultValue) {
        this.cells = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            cells.add(new ArrayCell<>(i, defaultValue));
        }
    }

    public Array(List<T> values) {
        this.cells = new ArrayList<>(values.size());
        for (int i = 0; i < values.size(); i++) {
            cells.add(new ArrayCell<>(i, values.get(i)));
        }
    }

    public Array(Stream<T> values) {
        this(values.toList());
    }

    @Override
    protected List<ArrayCell<T>> getCells() {
        return cells;
    }

    @Override
    protected Array<T> emptyCopy() {
        return new Array<>(cells.size(), null);
    }

    @Override
    public T get(Integer point) {
        return cells.get(point).getValue();
    }

    @Override
    public void set(Integer point, T value) {
        cells.get(point).setValue(value);
    }

    public <NT> Array<NT> map(Function<ArrayCell<T>, NT> mapper) {
        return super.abstractMap(mapper);
    }

    public int size() {
        return cells.size();
    }
}
