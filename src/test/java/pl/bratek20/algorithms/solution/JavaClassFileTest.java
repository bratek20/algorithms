package pl.bratek20.algorithms.solution;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JavaClassFileTest {
    private JavaClassFile create() {
        return new JavaClassFile("src/test/java/pl/bratek20/algorithms/solution/puzzles/SimpleImport.java");
    }

    @Test
    void shouldReturnImports() {
        var file = create();

        var imports = file.getImports();

        assertThat(imports).containsExactly("pl.bratek20.algorithms.solution.common.SomeClass");
    }

    @Test
    void shouldReturnClassDefinition() {
        var file = create();

        var definition = file.getClassDefinition();

        assertThat(definition).isEqualTo("""
        public class SimpleImport {
            SomeClass someClass;
        }
        """);
    }
}