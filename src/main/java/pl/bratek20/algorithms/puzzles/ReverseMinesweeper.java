package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.array.d2.Array2DReader;
import pl.bratek20.algorithms.common.array.d2.Array2DWriter;
import pl.bratek20.algorithms.common.puzzle.Puzzle;

// https://www.codingame.com/ide/puzzle/reverse-minesweeper
public class ReverseMinesweeper extends Puzzle {

    @Override
    public void solve() {
        int w = in.readInt();
        int h = in.readInt();
        var arr = Array2DReader.readChar(w, h, in);

        var result = arr.correctMap(cell -> {
            if (cell.getValue() != '.') {
                return '.';
            }
            var x = cell.getPoint().neighborsWithDiagonals().stream().mapToInt(p -> {
                if (arr.isInside(p) && arr.get(p) == 'x') {
                    return 1;
                }
                return 0;
            }).sum();
            return x > 0 ? (char) ('0' + x) : '.';
        });

        Array2DWriter.writeChar(out, result);
    }
}
