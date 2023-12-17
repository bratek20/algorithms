package pl.bratek20.algorithms.solution;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.*;
import java.util.List;

public class Compiler {
    private final CompilerConfig config;
    private final Set<String> alreadyImported = new HashSet<>();
    private final Set<String> externalImports = new HashSet<>();
    public Compiler(CompilerConfig config) {
        this.config = config;
    }

    public String compile(String puzzleName) {
        String filePath = config.modulesFolderPath + "/puzzles/" + puzzleName + ".java";
        var file = new JavaFile(filePath);

        var solutionBuilder = new FileContentBuilder();

        solutionBuilder
            .addLine("class Solution {")
            .addIndent(4);

        config.compileImports
            .forEach(importLine -> compileForImport(new Import(importLine), solutionBuilder));

        compileFile(file, solutionBuilder);

        if (config.attachMain) {
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

    private void compileFile(JavaFile file, FileContentBuilder builder) {
        file.getImports()
            .forEach(importLine -> compileForImport(importLine, builder));

        var content = file.getStaticDeclaration();
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
        var importFile = new JavaFile(path);
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

        return config.modulesFolderPath + pathPart + ".java";
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Compiler <puzzleName>");
            return;
        }

        var compiler = new Compiler(new CompilerConfig.Builder()
            .modulesFolderPath("src/main/java/pl/bratek20/algorithms")
            .attachMain(true)
            .compileImports("pl.bratek20.algorithms.common.puzzle.PuzzleSolver")
            .build()
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
