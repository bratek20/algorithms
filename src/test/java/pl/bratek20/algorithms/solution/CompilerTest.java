package pl.bratek20.algorithms.solution;

import org.junit.jupiter.api.Test;
import pl.bratek20.algorithms.solution.compiler.Compiler;
import pl.bratek20.algorithms.solution.compiler.CompilerConfig;

import static org.assertj.core.api.Assertions.assertThat;

class CompilerTest {
    private CompilerConfig.Builder builderWithBasePath() {
        return new CompilerConfig.Builder()
            .basePath("src/test/resources/solution/");
    }

    private Compiler createCompiler() {
        return new Compiler(builderWithBasePath().build());
    }

    private Compiler createCompilerWithMainAttached(boolean spyInput) {
        return new Compiler(builderWithBasePath()
            .attachMain(true)
            .spyInput(spyInput)
            .build()
        );
    }


    private Compiler createCompilerWithCompileImports(String ...imports) {
        return new Compiler(builderWithBasePath()
            .compileImports(imports)
            .build()
        );
    }

    private Compiler createCompilerWithImportWholePackage() {
        return new Compiler(builderWithBasePath()
            .importWholePackage(true)
            .build()
        );
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
            public static class NoImports {
                
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
            public static class SomeClass {
                Object someClassField;
            }
            
            public static class SimpleImport {
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
            public static class InPackageClass {
                Object inPackageClassField;
            }
            
            public static class InPackageImport {
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
             public static class SomeClass {
                Object someClassField;
            }
            
            public static class ClassReferencingOther {
                SomeClass someClass;
            }
            
            public static class ReferencingImport {
                ClassReferencingOther classReferencingOther;
            }
        }
        """);
    }

    @Test
    void shouldAttachMain() {
        //given
        var compiler = createCompilerWithMainAttached(false);

        //when
        var result = compiler.compile("NoImports");

        //then
        assertThat(result).isEqualToIgnoringWhitespace("""
        class Solution {
            public static class NoImports {
                
            }
            
            public static void main(String[] args) {        
                new PuzzleSolver().solve(new NoImports());
            }
        }
        """);
    }

    @Test
    void shouldAttachMainWithSpyInput() {
        //given
        var compiler = createCompilerWithMainAttached(true);

        //when
        var result = compiler.compile("NoImports");

        //then
        assertThat(result).isEqualToIgnoringWhitespace("""
        class Solution {
            public static class NoImports {
                
            }
            
            public static void main(String[] args) {        
                new PuzzleSolver(true).solve(new NoImports());
            }
        }
        """);
    }

    @Test
    void shouldAddClassesForCompileImports() {
        //given
        var compiler = createCompilerWithCompileImports("pl.bratek20.algorithms.common.SomeClass");

        //when
        var result = compiler.compile("NoImports");

        //then
        assertThat(result).isEqualToIgnoringWhitespace("""
        class Solution {
             public static class SomeClass {
                Object someClassField;
            }
            
            public static class NoImports {
                
            }
        }
        """);
    }

    @Test
    void shouldPutExternalImportsAsSolutionImports() {
        //given
        var compiler = createCompiler();

        //when
        var result = compiler.compile("ExternalImports");

        //then
        assertThat(result).isEqualToIgnoringWhitespace("""
        import java.util.List;
        import java.util.ArrayList;
        
        class Solution {
            public static class ExternalImports {
                
            }
        }
        """);
    }

    @Test
    void shouldImportClassAtMostOnce() {
        //given
        var compiler = createCompiler();

        //when
        var result = compiler.compile("SameImportInImportedClasses");

        //then
        assertThat(result).isEqualToIgnoringWhitespace("""
            class Solution {
                public static class A {
                    
                }
                
                public static class Class1ReferencingA {
                    A a;
                }
                
                public static class Class2ReferencingA {
                    A a;
                }
                
                public static class SameImportInImportedClasses {
                        Class1ReferencingA class1ReferencingA;
                        Class2ReferencingA class2ReferencingA;
                }
            }
            """);
    }

    @Test
    void shouldImportWholePackageEvenIfOnlyOneFileImported() {
        //given
        var compiler = createCompilerWithImportWholePackage();

        //when
        var result = compiler.compile("InPackageImport");

        //then
        assertThat(result).isEqualToIgnoringWhitespace("""
        class Solution {
            public static class InPackageClass {
                Object inPackageClassField;
            }
            
            public static class OtherInPackageClass {
                
            }
            
            public static class InPackageImport {
                InPackageClass inPackageClass;
            }
        }
        """);
    }
}