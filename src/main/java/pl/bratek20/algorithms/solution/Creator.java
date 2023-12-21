package pl.bratek20.algorithms.solution;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Creator {
    private final String srcPath;
    private final String testPath;

    public Creator(String srcPath, String testPath) {
        this.srcPath = srcPath;
        this.testPath = testPath;
    }

    @SneakyThrows
    public void create(String puzzleName) {
        var puzzlePath = Paths.get(srcPath + "pl/bratek20/algorithms/puzzles/" + puzzleName + ".java");
        Files.createDirectories(puzzlePath.getParent());
        Files.write(puzzlePath, getTemplate(puzzleName).getBytes());

        var puzzleTestPath = Paths.get(testPath + "pl/bratek20/algorithms/puzzles/" + puzzleName + "Test.java");
        Files.createDirectories(puzzleTestPath.getParent());
        Files.write(puzzleTestPath, getTestTemplate(puzzleName).getBytes());

    }

    private String getTemplate(String puzzleName) {
        return """
            package pl.bratek20.algorithms.puzzles;
            
            import pl.bratek20.algorithms.common.puzzle.Puzzle;
            
            // TODO puzzle URL
            public class %s extends Puzzle {
                @Override
                public void solve() {
                    //TODO
                }
            }
            """.formatted(puzzleName);
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

        var creator = new Creator("src/main/java/", "src/test/java/");

        var puzzleName = args[0];
        creator.create(puzzleName);

        System.out.println("Created puzzle: " + puzzleName);
    }
}
