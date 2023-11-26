package pl.bratek20.algorithms.solution;

import java.util.List;
import java.util.Optional;

public class Compiler {
    private final String modulesFolderPath;
    private final boolean attachMain;
    private final List<String> compileImports;
    private final List<String> solutionImports;

    public Compiler(String modulesFolderPath) {
        this(modulesFolderPath, false);
    }

    public Compiler(String modulesFolderPath, boolean attachMain) {
        this(modulesFolderPath, attachMain, List.of(), List.of());
    }

    public Compiler(String modulesFolderPath, boolean attachMain, List<String> compileImports, List<String> solutionImports) {
        this.modulesFolderPath = modulesFolderPath;
        this.attachMain = attachMain;
        this.compileImports = compileImports;
        this.solutionImports = solutionImports;
    }

    public String compile(String puzzleName) {
        String filePath = modulesFolderPath + "/puzzles/" + puzzleName + ".java";
        var file = new JavaClassFile(filePath);

        var contentBuilder = new FileContentBuilder();

        solutionImports
            .forEach(importLine -> contentBuilder.addLine("import %s;".formatted(importLine)));

        contentBuilder
            .addLine("class Solution {")
            .addIndent(4);

        compileImports
            .forEach(importLine -> compileForImport(new Import(importLine), contentBuilder));

        compileFile(file, contentBuilder);

        if (attachMain) {
            contentBuilder
                .addContent(new FileContent("""
                public static void main(String[] args) {
                    new PuzzleSolver().solve(new %s());
                }
                """.formatted(puzzleName)));
        }

        contentBuilder
            .removeIndent(4)
            .addLine("}");

        return contentBuilder.build().toString();
    }

    private void compileFile(JavaClassFile file, FileContentBuilder builder) {
        file.getImports()
            .forEach(importLine -> compileForImport(importLine, builder));

        var content = file.getStaticClassDeclaration();
        builder.addContent(content);
    }

    private void compileForImport(Import importLine, FileContentBuilder builder) {
        var importFilePath = importToPath(importLine);
        importFilePath.ifPresent(path -> {
            var importFile = new JavaClassFile(path);
            compileFile(importFile, builder);
        });
    }

    private Optional<String> importToPath(Import importLine) {
        var path = importLine.getPath();
        if (!path.startsWith("pl.bratek20.algorithms")) {
            return Optional.empty();
        }
        var pathPart = path
            .replace("pl.bratek20.algorithms", "")
            .replace(".", "/");

        return Optional.of(modulesFolderPath + pathPart + ".java");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Compiler <puzzleName>");
            return;
        }

        var compiler = new Compiler(
            "src/main/java/pl/bratek20/algorithms",
            true,
            List.of("pl.bratek20.algorithms.common.puzzle.PuzzleSolver"),
            List.of("java.util.Scanner")
        );

        System.out.println(compiler.compile(args[0]));
    }
}
