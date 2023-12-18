package pl.bratek20.algorithms.common.array.d2;

import pl.bratek20.algorithms.common.array.AbstractArray;
import pl.bratek20.algorithms.common.array.AbstractCell;
import pl.bratek20.algorithms.common.array.d1.Array;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Array2D<T> extends AbstractArray<T, Array2DPoint, Array2DCell<T>, Array2D<T>> {
    private final int columns;
    private final int rows;
    private final List<Array2DCell<T>> cells;

    public Array2D(int columns, int rows, T defaultValue) {
        this.columns = columns;
        this.rows = rows;
        this.cells = new ArrayList<>(columns * rows);
        fill(defaultValue);
    }

    private void fill(T defaultValue) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                var point = new Array2DPoint(i, j);
                cells.add(new Array2DCell<>(point, defaultValue));
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
        return cells.get(getIndex(row, column)).getValue();
    }

    @Override
    protected List<Array2DCell<T>> getCells() {
        return cells;
    }

    @Override
    protected <NT, NC extends AbstractCell<NT, Array2DPoint>, NA extends AbstractArray<NT, Array2DPoint, NC, NA>> NA emptyCopy() {
        return (NA) new Array2D<NT>(columns, rows, null);
    }

    public <NT> Array2D<NT> correctMap(Function<Array2DCell<T>, NT> mapper) {
        Array2D<NT> newArray = emptyCopy();
        forEach(cell -> newArray.set(cell.getPoint(), mapper.apply(cell)));
        return newArray;
    }

    @Override
    public T get(Array2DPoint point) {
        return get(point.row(), point.column());
    }

    public void set(int row, int column, T val) {
        cells.get(getIndex(row, column)).setValue(val);
    }

    private int getIndex(int row, int column) {
        return row * columns + column;
    }

    @Override
    public void set(Array2DPoint point, T val) {
        set(point.row(), point.column(), val);
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public Array<T> getColumn(int column) {
        Array<T> result = new Array<>(rows, null);
        for (int i = 0; i < rows; i++) {
            result.set(i, get(i, column));
        }
        return result;
    }

    public Array<T> getRow(int row) {
        Array<T> result = new Array<>(columns, null);
        for (int i = 0; i < columns; i++) {
            result.set(i, get(row, i));
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
