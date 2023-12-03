package pl.bratek20.algorithms.common.input;

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
}
