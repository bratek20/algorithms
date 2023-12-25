package pl.bratek20.algorithms.common.input;

import pl.bratek20.algorithms.common.array.d1.Array;
import pl.bratek20.algorithms.common.utils.Pair;

import java.util.List;
import java.util.function.Function;
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

    public <T> Array<T> readLines(int n, Function<Input, T> mapper) {
        return new Array<>(Stream.generate(() -> mapper.apply(this)).limit(n));
    }

    public Pair readIntPair() {
        var ints = readInts();
        return new Pair(ints.get(0), ints.get(1));
    }
}
