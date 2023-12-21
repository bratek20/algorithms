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
        leftRight.put(')', '(');
        leftRight.put('{', '}');
        leftRight.put('}', '{');
        leftRight.put('[', ']');
        leftRight.put(']', '[');
        leftRight.put('<', '>');
        leftRight.put('>', '<');
        leftRight.put('/', '\\');
        leftRight.put('\\', '/');

        topBottom.put('^', 'v');
        topBottom.put('v', '^');
        topBottom.put('A', 'V');
        topBottom.put('V', 'A');
        topBottom.put('w', 'm');
        topBottom.put('m', 'w');
        topBottom.put('W', 'M');
        topBottom.put('M', 'W');
        topBottom.put('u', 'n');
        topBottom.put('n', 'u');
        topBottom.put('/', '\\');
        topBottom.put('\\', '/');

    }

    @Override
    public void solve() {
        fillMaps();
        var leftTop = Array2DReader.readCharSquare(in);
        int n = leftTop.getRows();

        var rightTop = leftTop.reverseColumns().map(c -> {
            var value = c.getValue();
            if (leftRight.containsKey(value)) {
                return leftRight.get(value);
            }
            return value;
        });
        var top = leftTop.concatByColumn(rightTop);

        var bottom = top.reverseRows().map(c -> {
            var value = c.getValue();
            if (topBottom.containsKey(value)) {
                return topBottom.get(value);
            }
            return value;
        });

        var tile = top.concatByRow(bottom);
        tile = tile.removeColumn(n - 1);
        tile = tile.removeRow(n - 1);

        tile = tile.addColumn(0, '|');
        tile = tile.addColumn(tile.getColumns(), '|');
        tile = tile.addRow(0, '-');
        tile = tile.addRow(tile.getRows(), '-');
        tile.set(0, 0, '+');
        tile.set(0, tile.getColumns() - 1, '+');
        tile.set(tile.getRows() - 1, 0, '+');
        tile.set(tile.getRows() - 1, tile.getColumns() - 1, '+');

        var floorTop = tile.concatByColumn(tile);
        var floor = floorTop.concatByRow(floorTop);

        floor = floor.removeColumn(tile.getColumns());
        floor = floor.removeRow(tile.getRows());

        Array2DWriter.writeChar(out, floor);
    }
}
