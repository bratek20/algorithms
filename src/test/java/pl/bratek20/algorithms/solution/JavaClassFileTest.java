package pl.bratek20.algorithms.solution;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JavaClassFileTest {
    private JavaClassFile create() {
        return new JavaClassFile("src/test/resources/solution/puzzles/SimpleImport.java");
    }

    @Test
    void shouldReturnImports() {
        var file = create();

        var imports = file.getImports();

        assertThat(imports).containsExactly(new Import("import pl.bratek20.algorithms.common.SomeClass;"));
    }

    @Test
    void shouldReturnClassDefinition() {
        var file = create();

        var definition = file.getStaticClassDeclaration().toString();

        assertThat(definition).isEqualToIgnoringWhitespace("""
        public static class SimpleImport {
            SomeClass someClass;
        }
        """);
    }
}