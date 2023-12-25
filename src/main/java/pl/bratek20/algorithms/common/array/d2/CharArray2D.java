package pl.bratek20.algorithms.common.array.d2;

import pl.bratek20.algorithms.common.array.d1.Array;

public class CharArray2D extends Array2D<Character> {

    public CharArray2D(int columns, int rows, Character defaultValue) {
        super(columns, rows, defaultValue);
    }

    public CharArray2D(Array<String> lines) {
        super(lines.get(0).length(), lines.size(), null);
        for (int i = 0; i < lines.size(); i++) {
            var line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                set(i, j, line.charAt(j));
            }
        }
    }
}
