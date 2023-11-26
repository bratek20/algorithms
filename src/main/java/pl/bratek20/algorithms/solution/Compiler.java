package pl.bratek20.algorithms.solution;

public class Compiler {
    private final String modulesFolderPath;
    private final boolean attachMain;

    public Compiler(String modulesFolderPath, boolean attachMain) {
        this.modulesFolderPath = modulesFolderPath;
        this.attachMain = attachMain;
    }

    public String compile(String puzzleName) {
        String filePath = modulesFolderPath + "/puzzles/" + puzzleName + ".java";
        var file = new JavaClassFile(filePath);

        var contentBuilder = new FileContentBuilder();
        contentBuilder
            .addLine("class Solution {")
            .addIndent(4);

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
        var imports = file.getImports();
        imports.forEach(importLine -> {
            var importFilePath = importToPath(importLine);

            var importFile = new JavaClassFile(importFilePath);
            compileFile(importFile, builder);
        });

        builder.addContent(file.getClassDeclaration());
    }

    private String importToPath(Import importLine) {
        var path = importLine.getPath();
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

        var compiler = new Compiler("src/main/java/pl/bratek20/algorithms", true);
        System.out.println(compiler.compile(args[0]));
    }
}
