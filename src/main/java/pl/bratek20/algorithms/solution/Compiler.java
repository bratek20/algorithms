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
            .addIndent(4)
            .addContent(file.getClassDeclaration())
            .removeIndent(4)
            .addLine("}");

        return contentBuilder.build().toString();
    }
}
