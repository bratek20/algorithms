package pl.bratek20.algorithms.common.array2d;

import pl.bratek20.algorithms.common.input.Input;

public class Array2DReader {
    public static Array2D<Character> readCharRectangle(Input in) {
        var p = in.readIntPair();
        int w = p.getLeft();
        int h = p.getRight();
        return readChar(w, h, in);
    }

    public static <T> Array2D<Character> readCharSquare(Input in) {
        var w = in.readInt();
        return readChar(w, w, in);
    }

    public static Array2D<Character> readChar(int w, int h, Input in) {
        var result = new Array2D<>(w, h, ' ');
        for (int i = 0; i < h; i++) {
            var charArray = in.readLine().toCharArray();
            for (int j = 0; j < w; j++) {
                result.set(i, j, charArray[j]);
            }
        }
        return result;
    }
}
