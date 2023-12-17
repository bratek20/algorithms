package pl.bratek20.algorithms.common.array.d1;

import pl.bratek20.algorithms.common.array.AbstractArray;
import pl.bratek20.algorithms.common.array.AbstractCell;

import java.util.ArrayList;
import java.util.List;

public class Array<T> extends AbstractArray<T, Integer, ArrayCell<T>> {
    private final List<ArrayCell<T>> cells;

    public Array(int n, T defaultValue) {
        this.cells = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            cells.add(new ArrayCell<>(i, defaultValue));
        }
    }

    @Override
    protected List<ArrayCell<T>> getCells() {
        return cells;
    }

    @Override
    protected <NT, NC extends AbstractCell<NT, Integer>> AbstractArray<NT, Integer, NC> emptyCopy() {
        return (AbstractArray<NT, Integer, NC>) new Array<>(cells.size(), null);
    }


    @Override
    public T get(Integer point) {
        return cells.get(point).getValue();
    }

    @Override
    public void set(Integer point, T value) {
        cells.get(point).setValue(value);
    }
}
