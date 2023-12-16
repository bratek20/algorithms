package pl.bratek20.algorithms.solution;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.*;
import java.util.List;

public class Compiler {
    private final String modulesFolderPath;
    private final boolean attachMain;
    private final List<String> compileImports;
    private final List<String> alreadyImported = new ArrayList<>();
    private final Set<String> externalImports = new HashSet<>();

    public Compiler(String modulesFolderPath) {
        this(modulesFolderPath, false);
    }

    public Compiler(String modulesFolderPath, boolean attachMain) {
        this(modulesFolderPath, attachMain, List.of());
    }

    public Compiler(String modulesFolderPath, boolean attachMain, List<String> compileImports) {
        this.modulesFolderPath = modulesFolderPath;
        this.attachMain = attachMain;
        this.compileImports = compileImports;
    }

    public String compile(String puzzleName) {
        String filePath = modulesFolderPath + "/puzzles/" + puzzleName + ".java";
        var file = new JavaClassFile(filePath);

        var solutionBuilder = new FileContentBuilder();

        solutionBuilder
            .addLine("class Solution {")
            .addIndent(4);

        compileImports
            .forEach(importLine -> compileForImport(new Import(importLine), solutionBuilder));

        compileFile(file, solutionBuilder);

        if (attachMain) {
            solutionBuilder
                .addContent(new FileContent("""
                public static void main(String[] args) {
                    new PuzzleSolver().solve(new %s());
                }
                """.formatted(puzzleName)));
        }

        solutionBuilder
            .removeIndent(4)
            .addLine("}");

        var header = getExternalImportsHeader();

        return new FileContentBuilder()
            .addContent(header)
            .addContent(solutionBuilder.build())
            .build()
            .toString();
    }

    private FileContent getExternalImportsHeader() {
        var builder = new FileContentBuilder();
        externalImports
            .forEach(builder::addLine);
        return builder.build();
    }

    private void compileFile(JavaClassFile file, FileContentBuilder builder) {
        file.getImports()
            .forEach(importLine -> compileForImport(importLine, builder));

        var content = file.getStaticClassDeclaration();
        builder.addContent(content);
    }

    private void compileForImport(Import importLine, FileContentBuilder builder) {
        if (importLine.isExternal()) {
            externalImports.add(importLine.getValue());
            return;
        }

        var path = importToPath(importLine);
        if (alreadyImported.contains(path)) {
            return;
        }

        alreadyImported.add(path);
        var importFile = new JavaClassFile(path);
        compileFile(importFile, builder);
    }

    private String importToPath(Import importLine) {
        var path = importLine.getPath();
        if (!path.startsWith("pl.bratek20.algorithms")) {
            throw new RuntimeException("Cannot import class: " + path);
        }
        var pathPart = path
            .replace("pl.bratek20.algorithms", "")
            .replace(".", "/");

        return modulesFolderPath + pathPart + ".java";
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Compiler <puzzleName>");
            return;
        }

        var compiler = new Compiler(
            "src/main/java/pl/bratek20/algorithms",
            true,
            List.of("pl.bratek20.algorithms.common.puzzle.PuzzleSolver")
        );

        var puzzleName = args[0];
        var puzzle = compiler.compile(puzzleName);

        copyToClipboard(puzzle);
        System.out.println("Compiled puzzle " + puzzleName + " copied to clipboard");
    }

    private static void copyToClipboard(String text) {
        var clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        var transferable = new StringSelection(text);
        clipboard.setContents(transferable, null);
    }
}
