package pl.bratek20.algorithms.solution;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class CreatorTest {

    @Test
    void shouldCreateFile(@TempDir Path tempDir) {
        //given
        var basePath = tempDir.toString() + "/";
        var creator = new Creator(basePath);

        //when
        creator.create("SomePuzzle");

        //then
        var puzzlePath = basePath + "pl/bratek20/algorithms/puzzles/SomePuzzle.java";
        var puzzleFile = Path.of(puzzlePath);
        assertThat(puzzleFile)
            .exists()
            .hasContent("""
                package pl.bratek20.algorithms.puzzles;
                            
                import pl.bratek20.algorithms.common.puzzle.Puzzle;
                            
                // TODO puzzle URL
                public class SomePuzzle extends Puzzle {
                    @Override
                    public void solve() {
                        //TODO
                    }
                }
                """);
    }

}