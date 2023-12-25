package pl.bratek20.algorithms.solution.compiler;

import pl.bratek20.algorithms.solution.filecontent.FileContent;
import pl.bratek20.algorithms.solution.filecontent.FileContentBuilder;
import pl.bratek20.algorithms.solution.javafile.Import;
import pl.bratek20.algorithms.solution.javafile.JavaFile;
import pl.bratek20.algorithms.solution.javafile.JavaPackageFiles;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.*;

public class Compiler {
    private final CompilerConfig config;
    private final Set<String> alreadyCompiledFiles = new HashSet<>();
    private final Set<String> externalImports = new HashSet<>();

    public Compiler(CompilerConfig config) {
        this.config = config;
    }

    public String compile(String puzzleName) {
        String filePath = config.basePath + "pl/bratek20/algorithms/puzzles/" + puzzleName + ".java";
        var file = new JavaFile(filePath);

        var solutionBuilder = new FileContentBuilder();

        solutionBuilder
            .addLine("class Solution {")
            .addIndent(4);

        config.compileImports
            .forEach(importLine -> compileForImport(new Import(importLine), solutionBuilder));

        compileFile(file, solutionBuilder);

        if (config.attachMain) {
            var newPuzzleSolver = config.spyInput ? "new PuzzleSolver(true)" : "new PuzzleSolver()";

            solutionBuilder
                .addContent(new FileContent("""
                public static void main(String[] args) {
                    %s.solve(new %s());
                }
                """.formatted(newPuzzleSolver, puzzleName)));
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
        if (alreadyCompiledFiles.contains(file.getPath())) {
            return;
        }
        alreadyCompiledFiles.add(file.getPath());

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

        if (config.importWholePackage) {
            var packagePath = importLine.getPackageFilePath(config.basePath);
            var packageFiles = new JavaPackageFiles(packagePath);
            packageFiles.getFiles()
                .forEach(file -> compileFile(file, builder));
        }
        else {
            var path = importLine.getFilePath(config.basePath);
            var importFile = new JavaFile(path);
            compileFile(importFile, builder);
        }

    }
}
