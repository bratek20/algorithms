package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.array.Array;
import pl.bratek20.algorithms.common.array2d.Array2D;
import pl.bratek20.algorithms.common.array2d.Array2DReader;
import pl.bratek20.algorithms.common.puzzle.Puzzle;

import java.util.HashSet;
import java.util.Set;

// https://www.codingame.com/ide/puzzle/sudoku-validator
public class SudokuValidator extends Puzzle {

    @Override
    public void solve() {
        var arr = Array2DReader.readInt(9, 9, in);

        boolean result = true;
        int[] row = new int[]{0, 0, 0, 3, 3, 3, 6, 6, 6};
        int[] col = new int[]{0, 3, 6, 0, 3, 6, 0, 3, 6};
        for(int i = 0; i < 9; i++) {
            result &= validate(arr.getColumn(i));
            result &= validate(arr.getRow(i));
            result &= validate(arr.subArray(row[i], col[i],3,3));
        }

        out.println(result);
    }

    boolean validate(Array<Integer> arr) {
        Set<Integer> set = new HashSet<>();
        arr.forEach(cell -> set.add(cell.getValue()));
        return set.size() == 9;
    }

    boolean validate(Array2D<Integer> arr) {
        Set<Integer> set = new HashSet<>();
        arr.forEach(cell -> set.add(cell.getValue()));
        return set.size() == 9;
    }
}
