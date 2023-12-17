package pl.bratek20.algorithms.common.array2d;

public class Array2DCell<T> {
    private final Array2DPoint point;
    private T value;

    public Array2DCell(Array2DPoint point, T value) {
        this.point = point;
        this.value = value;
    }

    public Array2DPoint getPoint() {
        return point;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T newValue) {
        value = newValue;
    }
}
