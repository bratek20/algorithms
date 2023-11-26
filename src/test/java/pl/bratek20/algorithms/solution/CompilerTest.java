package pl.bratek20.algorithms.solution;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CompilerTest {
    private Compiler createCompiler() {
        return new Compiler("src/test/resources/solution");
    }

    private Compiler createCompilerWithMainAttached() {
        return new Compiler("src/test/resources/solution", true);
    }


    private Compiler createCompilerWithCompileImports(List<String> imports) {
        return new Compiler("src/test/resources/solution", false, imports, List.of());
    }

    private Compiler createCompilerWithSolutionImports(List<String> imports) {
        return new Compiler("src/test/resources/solution", false, List.of(), imports);
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

    @Test
    void shouldCompilePuzzleWithClassReferencingOther() {
        //given
        var compiler = createCompiler();

        //when
        var result = compiler.compile("ReferencingImport");

        //then
        assertThat(result).isEqualToIgnoringWhitespace("""
        class Solution {
             public class SomeClass {
                Object someClassField;
            }
            
            public class ClassReferencingOther {
                SomeClass someClass;
            }
            
            public class ReferencingImport {
                ClassReferencingOther classReferencingOther;
            }
        }
        """);
    }

    @Test
    void shouldAttachMain() {
        //given
        var compiler = createCompilerWithMainAttached();

        //when
        var result = compiler.compile("NoImports");

        //then
        assertThat(result).isEqualToIgnoringWhitespace("""
        class Solution {
            public class NoImports {
                
            }
            
            public static void main(String[] args) {        
                new PuzzleSolver().solve(new NoImports());
            }
        }
        """);
    }

    @Test
    void shouldAddClassesForCompileImports() {
        //given
        var compiler = createCompilerWithCompileImports(List.of("pl.bratek20.algorithms.common.SomeClass"));

        //when
        var result = compiler.compile("NoImports");

        //then
        assertThat(result).isEqualToIgnoringWhitespace("""
        class Solution {
             public class SomeClass {
                Object someClassField;
            }
            
            public class NoImports {
                
            }
        }
        """);
    }

    @Test
    void shouldSkipJavaImports() {
        //given
        var compiler = createCompiler();

        //when
        var result = compiler.compile("WithJavaImports");

        //then
        assertThat(result).isEqualToIgnoringWhitespace("""
        class Solution {
            public class WithJavaImports {
                
            }
        }
        """);
    }

    @Test
    void shouldAddSolutionImports() {
        //given
        var compiler = createCompilerWithSolutionImports(List.of("java.util.List", "java.util.ArrayList"));

        //when
        var result = compiler.compile("NoImports");

        //then
        assertThat(result).isEqualToIgnoringWhitespace("""
        import java.util.List;
        import java.util.ArrayList;
        
        class Solution {
            public class NoImports {
                
            }
        }
        """);
    }
}