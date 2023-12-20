package pl.bratek20.algorithms.puzzles;

import java.util.HashMap;
import java.util.Map;
import pl.bratek20.algorithms.common.array.d1.Array;
import pl.bratek20.algorithms.common.array.d2.Array2DReader;
import pl.bratek20.algorithms.common.array.d2.Array2DWriter;
import pl.bratek20.algorithms.common.puzzle.Puzzle;

// https://www.codingame.com/ide/puzzle/tile-a-floor
public class TileAFloor extends Puzzle {

    //• () {} [] <> If any of these are on the left side, then show its opposite-mate on the right side
    //• ^v AV wm WM un If any of these are on the top half, then show its opposite-mate on the bottom half
    //• /\ If either of these is on the top left quadrant, then show its opposite-mate on the two adjacent quadrants
    Map<Character, Character> leftRight = new HashMap<>();
    Map<Character, Character> topBottom = new HashMap<>();
    void fillMaps() {
        leftRight.put('(', ')');
        leftRight.put('{', '}');
        leftRight.put('[', ']');
        leftRight.put('<', '>');
        leftRight.put('/', '\\');
        leftRight.put('\\', '/');

        topBottom.put('^', 'v');
        topBottom.put('A', 'V');
        topBottom.put('w', 'm');
        topBottom.put('W', 'M');
        topBottom.put('u', 'n');
        topBottom.put('/', '\\');
        topBottom.put('\\', '/');

    }

    @Override
    public void solve() {
        var arr = Array2DReader.readCharSquare(in);
        int n = arr.getRows();

        var leftTop = arr;
        var rightTop = arr.reverseColumns().map(c -> {
            var value = c.getValue();
            if (leftRight.containsKey(value)) {
                return leftRight.get(value);
            }
            return value;
        });
        var leftBottom = arr.reverseRows().map(c -> {
            var value = c.getValue();
            if (topBottom.containsKey(value)) {
                return topBottom.get(value);
            }
            return value;
        });
        var rightBottom = rightTop.reverseRows().map(c -> {
            var value = c.getValue();
            if (topBottom.containsKey(value)) {
                return topBottom.get(value);
            }
            return value;
        });

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
