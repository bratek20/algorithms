package pl.bratek20.algorithms.common.array.d2;

import pl.bratek20.algorithms.common.input.Input;

public class Array2DReader {
    public static CharArray2D readCharRectangle(Input in) {
        var p = in.readIntPair();
        int w = p.getLeft();
        int h = p.getRight();
        return readChar(w, h, in);
    }

    public static CharArray2D readCharSquare(Input in) {
        var w = in.readInt();
        return readChar(w, w, in);
    }

    public static CharArray2D readChar(int w, int h, Input in) {
        var result = new CharArray2D(w, h, ' ');
        for (int i = 0; i < h; i++) {
            var charArray = in.readLine().toCharArray();
            for (int j = 0; j < w; j++) {
                result.set(i, j, charArray[j]);
            }
        }
        return result;
    }

    public static Array2D<Integer> readIntRectangle(Input in) {
        var p = in.readIntPair();
        int w = p.getLeft();
        int h = p.getRight();
        return readInt(w, h, in);
    }

    public static Array2D<Integer> readInt(int w, int h, Input in) {
        var result = new Array2D<>(w, h, 0);
        for (int i = 0; i < h; i++) {
            var ints = in.readInts();
            for (int j = 0; j < w; j++) {
                result.set(i, j, ints.get(j));
            }
        }
        return result;
    }

}
