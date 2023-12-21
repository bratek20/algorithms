package pl.bratek20.algorithms.solution;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;

public class Creator {
    private final String basePath;

    public Creator(String basePath) {
        this.basePath = basePath;
    }

    @SneakyThrows
    public void create(String puzzleName) {
        //create file in src/main/java/pl/bratek20/algorithms/puzzles
        var puzzlePath = Paths.get(basePath + "pl/bratek20/algorithms/puzzles/" + puzzleName + ".java");
        Files.createDirectories(puzzlePath.getParent());
        Files.write(puzzlePath, "hej".getBytes());
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Creator <puzzleName>");
            return;
        }

        var creator = new Creator("src/main/java/");

        var puzzleName = args[0];
        creator.create(puzzleName);

        System.out.println("Created puzzle: " + puzzleName);
    }
}
