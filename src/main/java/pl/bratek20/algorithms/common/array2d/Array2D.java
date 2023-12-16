package pl.bratek20.algorithms.common.array2d;

public class Array2D<T> {
    private final int width;
    private final int height;
    private final T[][] value;

    public Array2D(int width, int height, T defaultValue) {
        this.width = width;
        this.height = height;
        this.value = (T[][]) new Object[height][width];

        fill(defaultValue);
    }

    private void fill(T defaultValue) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                value[i][j] = defaultValue;
            }
        }
    }

    public T get(int i, int j) {
        return value[i][j];
    }

    public void set(int i, int j, T val) {
        value[i][j] = val;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
