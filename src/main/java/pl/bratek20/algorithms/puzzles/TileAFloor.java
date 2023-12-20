package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.array.d1.Array;
import pl.bratek20.algorithms.common.array.d2.Array2DReader;
import pl.bratek20.algorithms.common.array.d2.Array2DWriter;
import pl.bratek20.algorithms.common.puzzle.Puzzle;

// https://www.codingame.com/ide/puzzle/tile-a-floor
public class TileAFloor extends Puzzle {

    @Override
    public void solve() {
        var arr = Array2DReader.readCharSquare(in);
        int n = arr.getRows();

        var leftTop = arr;
        var rightTop = arr.reverseColumns();
        var leftBottom = arr.reverseRows();
        var rightBottom = arr.reverseRows().reverseColumns();

        var top = leftTop.concatByColumn(rightTop);
        var bottom = leftBottom.concatByColumn(rightBottom);

        var tile = top.concatByRow(bottom);
        tile = tile.removeColumn(n - 1);
        tile = tile.removeRow(n - 1);

        var floor = tile.concatByColumn(tile);
        floor = floor.concatByRow(floor);

        floor = floor.addColumn(0, new Array<>(floor.getRows(), '|'));
        floor = floor.addColumn(floor.getColumns(), new Array<>(floor.getRows(), '|'));
        floor = floor.addColumn(n * 2, new Array<>(floor.getRows(), '|'));

        floor = floor.addRow(0, new Array<>(floor.getColumns(), '-'));
        floor = floor.addRow(floor.getRows(), new Array<>(floor.getColumns(), '-'));
        floor = floor.addRow(n * 2, new Array<>(floor.getColumns(), '-'));

        floor.set(0, 0, '+');
        floor.set(0, floor.getColumns() - 1, '+');
        floor.set(floor.getRows() - 1, 0, '+');
        floor.set(floor.getRows() - 1, floor.getColumns() - 1, '+');

        int n2 = n * 2;
        floor.set(0, n2, '+');
        floor.set(n2, n2, '+');
        floor.set(n2, 0, '+');
        floor.set(n2, floor.getColumns() - 1, '+');
        floor.set(floor.getRows() - 1, n2, '+');

        Array2DWriter.writeChar(out, floor);
    }
}
