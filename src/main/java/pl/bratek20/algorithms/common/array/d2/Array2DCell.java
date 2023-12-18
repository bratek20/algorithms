package pl.bratek20.algorithms.common.array.d2;

import pl.bratek20.algorithms.common.array.AbstractCell;

public class Array2DCell<T> extends AbstractCell<T, Array2DPoint> {

    public Array2DCell(Array2DPoint point, T value) {
        super(point, value);
    }
}
