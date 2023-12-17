package pl.bratek20.algorithms.common.input;

import pl.bratek20.algorithms.common.utils.Pair;

import java.util.List;
import java.util.stream.Stream;

public class Input {
    private final NextLineProvider nextLineProvider;

    public Input(NextLineProvider nextLineProvider) {
        this.nextLineProvider = nextLineProvider;
    }

    public String readLine() {
        return nextLineProvider.readLine();
    }

    public int readInt() {
        return Integer.parseInt(readLine());
    }

    public List<Integer> readInts() {
        var line = readLine();
        var split = line.split(" ");
        return Stream.of(split).map(Integer::parseInt).toList();
    }

    public Pair readIntPair() {
        var ints = readInts();
        return new Pair(ints.get(0), ints.get(1));
    }
}
