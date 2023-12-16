package pl.bratek20.algorithms.common.array2d;

import pl.bratek20.algorithms.common.input.Input;

public class Array2DReader {
    public static <T> Array2D<Character> readChar(Input in) {
        var p = in.readIntPair();
        int w = p.getLeft();
        int h = p.getRight();
        var maze = new Array2D<>(w, h, ' ');

        for (int i = 0; i < h; i++) {
            var charArray = in.readLine().toCharArray();
            for (int j = 0; j < w; j++) {
                maze.set(i, j, charArray[j]);
            }
        }

        return maze;
    }
}
