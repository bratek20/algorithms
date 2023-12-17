package pl.bratek20.algorithms.common.output;

public class SystemOutputProducer implements OutputProducer {

    @Override
    public void print(char c) {
        System.out.print(c);
    }

    @Override
    public void print(String s) {
        System.out.print(s);
    }
}
