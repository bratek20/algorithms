package pl.bratek20.algorithms.solution.compiler;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CompilerTest {
    private CompileArgs.Builder builderWithBasePath(String puzzleName) {
        return new CompileArgs.Builder()
            .puzzleName(puzzleName)
            .basePath("src/test/resources/solution/");
    }

    private String compile(String puzzleName) {
        return new Compiler().compile(builderWithBasePath(puzzleName).build());
    }

    private String compileWithMainAttached(String puzzleName, boolean spyInput) {
        return new Compiler().compile(builderWithBasePath(puzzleName)
            .attachMain(true)
            .spyInput(spyInput)
            .build()
        );
    }


    private String compileWithCompileImports(String puzzleName, String ...imports) {
        return new Compiler().compile(builderWithBasePath(puzzleName)
            .compileImports(imports)
            .build()
        );
    }

    private String compileWithImportWholePackage(String puzzleName) {
        return new Compiler().compile(builderWithBasePath(puzzleName)
            .importWholePackage(true)
            .build()
        );
    }


    @Test
    void shouldCompilePuzzleWithNoImports() {
        //when
        var result = compile("NoImports");

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
        //when
        var result = compile("SimpleImport");

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
        //when
        var result = compile("InPackageImport");

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
        //when
        var result = compile("ReferencingImport");

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
        //when
        var result = compileWithMainAttached("NoImports", false);

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
        //when
        var result = compileWithMainAttached("NoImports", true);

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
        //when
        var result = compileWithCompileImports("NoImports", "pl.bratek20.algorithms.common.SomeClass");

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
        //when
        var result = compile("ExternalImports");

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
        //when
        var result = compile("SameImportInImportedClasses");

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
        //when
        var result = compileWithImportWholePackage("InPackageImport");

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