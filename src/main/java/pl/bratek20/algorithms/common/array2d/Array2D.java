package pl.bratek20.algorithms.common.array2d;

import pl.bratek20.algorithms.common.utils.Pair;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

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

    public Pair fix(int i, int j) {
        return new Pair(fixI(i), fixJ(j));
    }

    private int fixI(int i) {
        return ((i % height) + height) % height;
    }

    private int fixJ(int j) {
        return ((j % width) + width) % width;
    }

    public T get(int i, int j) {
        return value[i][j];
    }

    public T get(Pair pair) {
        return get(pair.getLeft(), pair.getRight());
    }

    public void set(int i, int j, T val) {
        value[i][j] = val;
    }

    public void set(Pair pair, T val) {
        set(pair.getLeft(), pair.getRight(), val);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Optional<Pair> find(Predicate<T> predicate) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                var val = value[i][j];
                if (predicate.test(val)) {
                    return Optional.of(new Pair(i, j));
                }
            }
        }
        return Optional.empty();
    }

    public void forEach(BiConsumer<T, Pair> consumer) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                consumer.accept(value[i][j], new Pair(i, j));
            }
        }
    }
}
