package pl.bratek20.algorithms.common.output;

public class Output {
    private final OutputProducer outputProducer;

    public Output(OutputProducer outputProducer) {
        this.outputProducer = outputProducer;
    }

    public void print(char c) {
        outputProducer.print(c);
    }

    public void print(String s) {
        outputProducer.print(s);
    }

    public void print(boolean b) {
        print(b ? "true" : "false");
    }

    public void print(int i) {
        print(String.valueOf(i));
    }

    public void println(String s) {
        outputProducer.print(s + "\n");
    }

    public void println(boolean b) {
        print(b);
        println();
    }

    public void println(int i) {
        print(i);
        println();
    }

    public void println() {
        outputProducer.print('\n');
    }
}
