package pl.bratek20.algorithms.common.input;

public class SpyingNextLineProvider implements NextLineProvider {
    private final NextLineProvider nextLineProvider;

    public SpyingNextLineProvider(NextLineProvider nextLineProvider) {
        this.nextLineProvider = nextLineProvider;
    }

    @Override
    public String readLine() {
        var line = nextLineProvider.readLine();
        System.err.println(line);
        return line;
    }
}
