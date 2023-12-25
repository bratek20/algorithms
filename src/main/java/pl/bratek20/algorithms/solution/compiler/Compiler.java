package pl.bratek20.algorithms.solution.compiler;

import pl.bratek20.algorithms.solution.filecontent.FileContent;
import pl.bratek20.algorithms.solution.filecontent.FileContentBuilder;
import pl.bratek20.algorithms.solution.javafile.Import;
import pl.bratek20.algorithms.solution.javafile.JavaFile;
import pl.bratek20.algorithms.solution.javafile.JavaPackageFiles;

import java.util.*;

public class Compiler {
    private final Set<String> alreadyCompiledFiles = new HashSet<>();
    private final Set<String> externalImports = new HashSet<>();
    private CompileArgs args;

    public String compile(CompileArgs args) {
        this.args = args;

        String filePath = args.basePath() + "pl/bratek20/algorithms/puzzles/" + args.puzzleName() + ".java";
        var file = new JavaFile(filePath);

        var solutionBuilder = new FileContentBuilder();

        solutionBuilder
            .addLine("class Solution {")
            .addIndent(4);

        args.compileImports()
            .forEach(importLine -> compileForImport(new Import(importLine), solutionBuilder));

        compileFile(file, solutionBuilder);

        if (args.attachMain()) {
            var newPuzzleSolver = args.spyInput() ? "new PuzzleSolver(true)" : "new PuzzleSolver()";

            solutionBuilder
                .addContent(new FileContent("""
                public static void main(String[] args) {
                    %s.solve(new %s());
                }
                """.formatted(newPuzzleSolver, args.puzzleName())));
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

        if (args.importWholePackage()) {
            var packagePath = importLine.getPackageFilePath(args.basePath());
            var packageFiles = new JavaPackageFiles(packagePath);
            packageFiles.getFiles()
                .forEach(file -> compileFile(file, builder));
        }
        else {
            var path = importLine.getFilePath(args.basePath());
            var importFile = new JavaFile(path);
            compileFile(importFile, builder);
        }

    }
}
