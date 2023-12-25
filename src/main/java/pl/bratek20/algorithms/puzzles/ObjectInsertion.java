package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.array.d2.Array2DPoint;
import pl.bratek20.algorithms.common.array.d2.Array2DReader;
import pl.bratek20.algorithms.common.array.d2.Array2DWriter;
import pl.bratek20.algorithms.common.array.d2.CharArray2D;
import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.utils.Variable;

// https://www.codingame.com/ide/puzzle/object-insertion
public class ObjectInsertion extends Puzzle {
    @Override
    public void solve() {
        var objectSize = in.readIntPair();
        var object = Array2DReader.readChar(objectSize.getRight(), objectSize.getLeft(), in);

        var gridSize = in.readIntPair();
        var grid = Array2DReader.readChar(gridSize.getRight(), gridSize.getLeft(), in);

        Variable<Integer> count = new Variable<>(0);
        Variable<Array2DPoint> point = new Variable<>(new Array2DPoint(0, 0));

        grid.forEach(cell -> {
            if (fits(object, grid, cell.getPoint())) {
                count.set(count.get() + 1);
                point.set(cell.getPoint());
            }
        });

        out.println(count.get());
        if (count.get() == 1) {
            fill(object, grid, point.get());
            Array2DWriter.writeChar(out, grid);
        }
    }

    boolean fits(CharArray2D object, CharArray2D grid, Array2DPoint gridPoint) {
        Variable<Boolean> fits = new Variable<>(true);
        object.forEach(cell -> {
            var point = gridPoint.add(cell.getPoint());
            if (cell.getValue() == '*' && (!grid.isInside(point) || grid.get(point) != '.')) {
                fits.set(false);
            }
        });
        return fits.get();
    }

    void fill(CharArray2D object, CharArray2D grid, Array2DPoint gridPoint) {
        object.forEach(cell -> {
            var point = gridPoint.add(cell.getPoint());
            if (cell.getValue() == '*') {
                grid.set(point, '*');
            }
        });
    }
}
