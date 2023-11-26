package pl.bratek20.algorithms.solution;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CompilerTest {
    private Compiler createCompiler() {
        return new Compiler("src/test/resources/solution");
    }

    @Test
    void shouldCompilePuzzleWithNoImports() {
        //given
        var compiler = createCompiler();

        //when
        var result = compiler.compile("NoImports");

        //then
        assertThat(result).isEqualToIgnoringWhitespace("""
        class Solution {
            public class NoImports {
                
            }
        }
        """);
    }

    @Test
    void shouldCompilePuzzleWithSimpleImport() {
        //given
        var compiler = createCompiler();

        //when
        var result = compiler.compile("SimpleImport");

        //then
        assertThat(result).isEqualToIgnoringWhitespace("""
        class Solution {
            public class SomeClass {
                Object someClassField;
            }
            
            public class SimpleImport {
                SomeClass someClass;
            }
        }
        """);
    }

    @Test
    void shouldCompilePuzzleWithInPackageImport() {
        //given
        var compiler = createCompiler();

        //when
        var result = compiler.compile("InPackageImport");

        //then
        assertThat(result).isEqualToIgnoringWhitespace("""
        class Solution {
            public class InPackageClass {
                Object inPackageClassField;
            }
            
            public class InPackageImport {
                InPackageClass inPackageClass;
            }
        }
        """);
    }
}