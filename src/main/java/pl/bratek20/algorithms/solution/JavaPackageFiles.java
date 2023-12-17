package pl.bratek20.algorithms.solution;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class JavaPackageFiles {
    private final String packagePath;

    public JavaPackageFiles(String packagePath) {
        this.packagePath = packagePath;
    }

    @SneakyThrows
    public List<JavaFile> getFiles() {
        try (Stream<Path> paths = Files.walk(Paths.get(packagePath))) {
            return paths.map(Path::toString)
                .filter(string -> string.endsWith(".java"))
                .map(JavaFile::new)
                .toList();
        }
    }
}
