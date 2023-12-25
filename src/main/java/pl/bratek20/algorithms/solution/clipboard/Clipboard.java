package pl.bratek20.algorithms.solution.clipboard;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class Clipboard {
    public void copy(String text) {
        var clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        var transferable = new StringSelection(text);
        clipboard.setContents(transferable, null);
    }
}
