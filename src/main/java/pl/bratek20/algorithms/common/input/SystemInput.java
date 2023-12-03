package pl.bratek20.algorithms.common.input;

import java.util.Scanner;

public class SystemInput implements Input {

    @Override
    public String readLine() {
        var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public int readInt() {
        return Integer.parseInt(readLine());
    }
}
