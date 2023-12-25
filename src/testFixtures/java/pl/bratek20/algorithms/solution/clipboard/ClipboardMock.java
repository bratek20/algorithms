package pl.bratek20.algorithms.solution.clipboard;

import lombok.Getter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClipboardMock {
    @Getter
    private final Clipboard value = mock(Clipboard.class);

    public void assertCopyCalledOnce(String text) {
        verify(value).copy(text);
    }
}
