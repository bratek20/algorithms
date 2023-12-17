package pl.bratek20.algorithms.common.array.d1;

import pl.bratek20.algorithms.common.array.AbstractCell;

public class ArrayCell<T> extends AbstractCell<T, Integer> {

    public ArrayCell(Integer point, T value) {
        super(point, value);
    }
    
    public int getIndex() {
        return getPoint();
    }
}
