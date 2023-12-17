package pl.bratek20.algorithms.common.array2d;

import pl.bratek20.algorithms.common.array.d1.Array;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Array2D<T> {
    private final int columns;
    private final int rows;
    private final Array2DCell<T>[][] cells;

    public Array2D(int columns, int rows, T defaultValue) {
        this.columns = columns;
        this.rows = rows;
        this.cells = new Array2DCell[rows][columns];

        fill(defaultValue);
    }

    private void fill(T defaultValue) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                var point = new Array2DPoint(i, j);
                cells[i][j] = new Array2DCell<>(point, defaultValue);
            }
        }
    }

    public Array2DPoint fix(int row, int column) {
        return new Array2DPoint(fixRow(row), fixColumn(column));
    }

    public Array2DPoint fix(Array2DPoint point) {
        return fix(point.row(), point.column());
    }

    public boolean isInside(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public boolean isInside(Array2DPoint point) {
        return isInside(point.row(), point.column());
    }

    private int fixRow(int row) {
        return ((row % rows) + rows) % rows;
    }

    private int fixColumn(int column) {
        return ((column % columns) + columns) % columns;
    }

    public T get(int row, int column) {
        return cells[row][column].getValue();
    }

    public T get(Array2DPoint point) {
        return get(point.row(), point.column());
    }

    public void set(int row, int column, T val) {
        cells[row][column].setValue(val);
    }

    public void set(Array2DPoint point, T val) {
        set(point.row(), point.column(), val);
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public Optional<Array2DPoint> find(Predicate<T> predicate) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                var cell = cells[i][j];
                if (predicate.test(cell.getValue())) {
                    return Optional.of(cell.getPoint());
                }
            }
        }
        return Optional.empty();
    }

    public void forEach(Consumer<Array2DCell<T>> consumer) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                consumer.accept(cells[i][j]);
            }
        }
    }

    public <NT> Array2D<NT> map(Function<Array2DCell<T>, NT> mapper) {
        Array2D<NT> newArray = new Array2D<>(columns, rows, null);
        forEach(cell -> newArray.set(cell.getPoint(), mapper.apply(cell)));
        return newArray;
    }

    public Array<Integer> getColumn(int column) {
        Array<Integer> result = new Array<>(rows, 0);
        for (int i = 0; i < rows; i++) {
            result.set(i, (Integer) cells[i][column].getValue());
        }
        return result;
    }

    public Array<Integer> getRow(int row) {
        Array<Integer> result = new Array<>(columns, 0);
        for (int i = 0; i < columns; i++) {
            result.set(i, (Integer) cells[row][i].getValue());
        }
        return result;
    }

    public Array2D<T> subArray(int startRow, int startColumn, int rows, int columns) {
        Array2D<T> result = new Array2D<>(rows, columns, null);
        for (int i = startRow; i < startRow + columns; i++) {
            for (int j = startColumn; j < startColumn + rows; j++) {
                result.set(i - startRow, j - startColumn, get(i, j));
            }
        }
        return result;
    }
}
