package pl.bratek20.algorithms.common.input;

import java.util.Scanner;

public class SystemInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public int readInt() {
        return Integer.parseInt(readLine());
    }
}
