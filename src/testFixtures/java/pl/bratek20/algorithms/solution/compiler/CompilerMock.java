package pl.bratek20.algorithms.solution.compiler;

import lombok.Getter;

import static org.mockito.Mockito.*;

public class CompilerMock {
    @Getter
    private final Compiler value = mock(Compiler.class);

    public void assertCompileCalledOnce(CompileArgs args) {
        verify(value).compile(args);
    }

    public void setCompileResult(String compiledPuzzle) {
        when(value.compile(any())).thenReturn(compiledPuzzle);
    }
}
