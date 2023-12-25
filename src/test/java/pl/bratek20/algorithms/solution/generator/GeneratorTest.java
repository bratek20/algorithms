package pl.bratek20.algorithms.solution.generator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import pl.bratek20.algorithms.solution.generator.Generator;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class GeneratorTest {
    static final String SOME_PUZZLE_URL = "https://www.codingame.com/ide/puzzle/some-puzzle";

    @Test
    void shouldGeneratePuzzleFromURL(@TempDir Path tempDir) {
        //given
        var basePath = tempDir.toString() + "/";
        var generator = new Generator(basePath, basePath);

        //when
        generator.generate(SOME_PUZZLE_URL);

        //then
        var puzzlePath = basePath + "pl/bratek20/algorithms/puzzles/SomePuzzle.java";
        var puzzleFile = Path.of(puzzlePath);
        assertThat(puzzleFile)
            .exists()
            .hasContent("""
                package pl.bratek20.algorithms.puzzles;
                            
                import pl.bratek20.algorithms.common.puzzle.Puzzle;
                            
                // https://www.codingame.com/ide/puzzle/some-puzzle
                public class SomePuzzle extends Puzzle {
                    @Override
                    public void solve() {
                        //TODO
                    }
                }
                """);
    }

    @Test
    void shouldGeneratePuzzleTestFromURL(@TempDir Path tempDir) {
        //given
        var basePath = tempDir.toString() + "/";
        var generator = new Generator(basePath, basePath);

        //when
        generator.generate(SOME_PUZZLE_URL);

        //then
        var puzzleTestPath = basePath + "pl/bratek20/algorithms/puzzles/SomePuzzleTest.java";
        var puzzleFile = Path.of(puzzleTestPath);
        assertThat(puzzleFile)
            .exists()
            .hasContent("""
                package pl.bratek20.algorithms.puzzles;
                                
                import pl.bratek20.algorithms.common.puzzle.Puzzle;
                import pl.bratek20.algorithms.common.puzzle.PuzzleTest;
                                
                import java.util.List;
                                
                class SomePuzzleTest extends PuzzleTest {
                                
                    @Override
                    protected Puzzle createSolution() {
                        return new SomePuzzle();
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
                """);
    }

}