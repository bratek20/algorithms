package pl.bratek20.algorithms.solution;

import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JavaClassFile {
    private final String path;
    private FileContent content;

    public JavaClassFile(String path) {
        this.path = path;
        readContent();
    }

    @SneakyThrows
    private void readContent() {
        byte[] encodedBytes = Files.readAllBytes(Paths.get(path));
        var contentString = new String(encodedBytes, StandardCharsets.UTF_8);
        content = new FileContent(contentString);
    }

    public List<Import> getImports() {
        return content.findLines(line -> line.startsWith("import"))
                .stream()
                .map(Import::new)
                .toList();
    }

    public FileContent getStaticClassDeclaration() {
        var classDeclarationStartLine = content.findLine(line -> line.contains("class"))
            .orElseGet(() -> content.findLine(line -> line.contains("interface"))
                .orElseThrow(() -> new RuntimeException("Class declaration not found in file: " + path)));

        var firstLine = classDeclarationStartLine
            .replace("class", "static class")
            .replace("interface", "static interface");

        return new FileContentBuilder()
            .addLine(firstLine)
            .addContent(content.splitAfterLine(classDeclarationStartLine))
            .build();
    }


}
