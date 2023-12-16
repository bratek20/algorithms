package pl.bratek20.algorithms.common.array2d;

import pl.bratek20.algorithms.common.output.Output;

public class Array2DWriter {
    public static void writeChar(Output out, Array2D<Character> array2D) {
        array2D.forEach((c, p) -> {
            out.print(c);
            if (p.getRight() == array2D.getWidth() - 1) {
                out.println();
            }
        });
    }
}
