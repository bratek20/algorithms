package pl.bratek20.algorithms.common.array.d2;

import pl.bratek20.algorithms.common.output.Output;

public class Array2DWriter {
    public static void writeChar(Output out, Array2D<Character> array2D) {
        array2D.forEach(cell -> {
            out.print(cell.getValue());
            if (cell.getPoint().column() == array2D.getColumns() - 1) {
                out.println();
            }
        });
    }
}
