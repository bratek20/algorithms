package pl.bratek20.algorithms.solution;

public class Compiler {
    private final String modulesFolderPath;
    public Compiler(String modulesFolderPath) {
        this.modulesFolderPath = modulesFolderPath;
    }

    public String compile(String puzzleName) {
        String filePath = modulesFolderPath + "/puzzles/" + puzzleName + ".java";
        var file = new JavaClassFile(filePath);

        var contentBuilder = new FileContentBuilder();
        contentBuilder
            .addLine("class Solution {")
            .addIndent(4);

        var imports = file.getImports();
        imports.forEach(importLine -> {
            String className = importLine.getClassName();

            var importFile = new JavaClassFile(modulesFolderPath + "/common/" + className + ".java");
            contentBuilder.addContent(importFile.getClassDeclaration());
        });

        contentBuilder
            .addContent(file.getClassDeclaration())
            .removeIndent(4)
            .addLine("}");

        return contentBuilder.build().toString();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Compiler <puzzleName>");
            return;
        }

        var compiler = new Compiler("src/main/java/pl/bratek20/algorithms");
        System.out.println(compiler.compile(args[0]));
    }
}
