package pl.bratek20.algorithms.solution;

import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JavaClassFile {
    private final String path;
    private String content;

    public JavaClassFile(String path) {
        this.path = path;
        readContent();
    }

    @SneakyThrows
    private void readContent() {
        byte[] encodedBytes = Files.readAllBytes(Paths.get(path));
        content = new String(encodedBytes, StandardCharsets.UTF_8);
    }

    public List<String> getImports() {

        return null;
    }

    public String getClassDefinition() {
        return null;
    }
}
