package pl.bratek20.algorithms.solution;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class FileContent {
    private final String content;

    public FileContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }

    public List<String> findLines(Predicate<String> predicate) {
        return getLines().stream()
            .filter(predicate)
            .toList();
    }

    public Optional<String> findLine(Predicate<String> predicate) {
        return getLines().stream()
            .filter(predicate)
            .findFirst();
    }

    public List<String> getLines() {
        return List.of(content.split("(\r\n|\n)"));
    }

    public FileContent splitAfterLine(String line) {
        var lines = getLines();
        var lineIndex = lines.indexOf(line);
        var linesToTake = lines.subList(lineIndex + 1, lines.size());
        var newContent = String.join("\n", linesToTake);
        return new FileContent(newContent);
    }
}
