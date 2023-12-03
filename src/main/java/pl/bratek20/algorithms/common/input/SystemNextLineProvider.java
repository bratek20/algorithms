package pl.bratek20.algorithms.common.input;

import java.util.Scanner;

public class SystemNextLineProvider implements NextLineProvider {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
