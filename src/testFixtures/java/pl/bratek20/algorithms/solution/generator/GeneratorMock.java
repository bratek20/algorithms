package pl.bratek20.algorithms.solution.generator;

import lombok.Getter;

import static org.mockito.Mockito.*;

public class GeneratorMock {
    @Getter
    private final Generator value = mock(Generator.class);

    public void assertGenerateCalledOnce(String puzzleUrl) {
        verify(value, times(1)).generate(puzzleUrl);
    }
}
