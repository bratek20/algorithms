package pl.bratek20.algorithms.common.array;

public class AbstractCell<T, P> {
    private final P point;
    private T value;

    public AbstractCell(P point, T value) {
        this.point = point;
        this.value = value;
    }

    public P getPoint() {
        return point;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}
