package pl.bratek20.algorithms.common.input;

import pl.bratek20.algorithms.common.utils.Pair;

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

    public Pair readIntPair() {
        var line = readLine();
        var split = line.split(" ");
        return new Pair(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }
}
