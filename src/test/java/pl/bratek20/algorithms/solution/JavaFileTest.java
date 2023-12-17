package pl.bratek20.algorithms.solution;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JavaFileTest {
    private JavaFile create(String fileName) {
        return new JavaFile("src/test/resources/solution/puzzles/" + fileName + ".java");
    }

    @Test
    void shouldReturnImports() {
        var file = create("SimpleImport");

        var imports = file.getImports();

        assertThat(imports).containsExactly(new Import("import pl.bratek20.algorithms.common.SomeClass;"));
    }

    @Test
    void shouldReturnStaticClassDefinition() {
        var file = create("SimpleImport");

        var definition = file.getStaticDeclaration().toString();

        assertThat(definition).isEqualToIgnoringWhitespace("""
        public static class SimpleImport {
            SomeClass someClass;
        }
        """);
    }

    @Test
    void shouldReturnStaticInterfaceDefinition() {
        var file = create("SomeInterface");

        var definition = file.getStaticDeclaration().toString();

        assertThat(definition).isEqualToIgnoringWhitespace("""
        public static interface SomeInterface {
            
        }
        """);
    }

    @Test
    void shouldReturnStaticRecordDefinition() {
        var file = create("SomeRecord");

        var definition = file.getStaticDeclaration().toString();

        assertThat(definition).isEqualToIgnoringWhitespace("""
        public static record SomeRecord() {
            
        }
        """);
    }
}