package pl.bratek20.algorithms.common.input;

import java.util.Scanner;

public class SystemInput implements Input {

    @Override
    public String nextLine() {
        var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
