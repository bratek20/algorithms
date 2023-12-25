package pl.bratek20.algorithms.solution.generator;

import lombok.SneakyThrows;
import pl.bratek20.algorithms.solution.casing.CaseUtils;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Generator {
    private final String srcPath;
    private final String testPath;

    public Generator(String srcPath, String testPath) {
        this.srcPath = srcPath;
        this.testPath = testPath;
    }

    @SneakyThrows
    public void generate(String puzzleUrl) {
        var puzzleName = extractPuzzleName(puzzleUrl);

        var puzzlePath = Paths.get(srcPath + "pl/bratek20/algorithms/puzzles/" + puzzleName + ".java");
        Files.createDirectories(puzzlePath.getParent());
        Files.write(puzzlePath, getTemplate(puzzleName, puzzleUrl).getBytes());

        var puzzleTestPath = Paths.get(testPath + "pl/bratek20/algorithms/puzzles/" + puzzleName + "Test.java");
        Files.createDirectories(puzzleTestPath.getParent());
        Files.write(puzzleTestPath, getTestTemplate(puzzleName).getBytes());

    }

    private String extractPuzzleName(String url) {
        var parts = url.split("/");
        var puzzleNameKebabCase = parts[parts.length-1];
        return CaseUtils.kebabToPascal(puzzleNameKebabCase);
    }

    private String getTemplate(String puzzleName, String puzzleUrl) {
        return """
            package pl.bratek20.algorithms.puzzles;
            
            import pl.bratek20.algorithms.common.puzzle.Puzzle;
            
            // %s
            public class %s extends Puzzle {
                @Override
                public void solve() {
                    //TODO
                }
            }
            """.formatted(puzzleUrl, puzzleName);
    }

    private String getTestTemplate(String puzzleName) {
        return """
            package pl.bratek20.algorithms.puzzles;
            
            import pl.bratek20.algorithms.common.puzzle.Puzzle;
            import pl.bratek20.algorithms.common.puzzle.PuzzleTest;
            
            import java.util.List;
            
            class %sTest extends PuzzleTest {
            
                @Override
                protected Puzzle createSolution() {
                    return new %s();
                }
            
                @Override
                protected List<TestData> testData() {
                    return List.of(
                        new TestData("example",
                            ""\"
                            TODO input
                            ""\",
                            ""\"
                            TODO output
                            ""\"
                        )
                    );
                }
            }
            """.formatted(puzzleName, puzzleName);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Creator <puzzleName>");
            return;
        }

        var creator = new Generator("src/main/java/", "src/test/java/");

        var puzzleName = args[0];
        creator.generate(puzzleName);

        System.out.println("Created puzzle: " + puzzleName);
    }
}
