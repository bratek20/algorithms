package pl.bratek20.algorithms.common.output;

public class SystemOutput implements Output {

    @Override
    public void print(char c) {
        System.out.print(c);
    }

    @Override
    public void println() {
        System.out.println();
    }
}
