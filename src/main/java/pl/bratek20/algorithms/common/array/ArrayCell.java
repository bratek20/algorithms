package pl.bratek20.algorithms.common.array;

public class ArrayCell<T> {
    private final int index;
    private T value;

    public ArrayCell(int index, T value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
