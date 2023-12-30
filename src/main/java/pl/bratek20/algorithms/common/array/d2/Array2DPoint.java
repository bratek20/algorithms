package pl.bratek20.algorithms.common.array.d2;

import java.util.List;

public record Array2DPoint(int row, int column) {
    public List<Array2DPoint> neighbors() {
        return List.of(
                new Array2DPoint(row - 1, column),
                new Array2DPoint(row + 1, column),
                new Array2DPoint(row, column - 1),
                new Array2DPoint(row, column + 1)
        );
    }

    public List<Array2DPoint> neighborsWithDiagonals() {
        return List.of(
                new Array2DPoint(row - 1, column),
                new Array2DPoint(row + 1, column),
                new Array2DPoint(row, column - 1),
                new Array2DPoint(row, column + 1),
                new Array2DPoint(row - 1, column - 1),
                new Array2DPoint(row - 1, column + 1),
                new Array2DPoint(row + 1, column - 1),
                new Array2DPoint(row + 1, column + 1)
        );
    }

    public Array2DPoint add(Array2DPoint other) {
        return new Array2DPoint(row + other.row, column + other.column);
    }

    public Array2DPoint subtract(Array2DPoint other) {
        return new Array2DPoint(row - other.row, column - other.column);
    }
}
