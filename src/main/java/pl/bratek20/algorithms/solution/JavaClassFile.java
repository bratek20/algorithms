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

    public FileContent getClassDeclaration() {
        var classDeclarationStartLine = content.findLine(line -> line.contains(" class ")).orElseThrow();
        return content.splitFromLine(classDeclarationStartLine);
    }


}
