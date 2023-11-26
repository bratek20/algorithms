package pl.bratek20.algorithms.solution;

public class FileContentBuilder {
    private final StringBuilder current = new StringBuilder();
    private int spaces = 0;

    public FileContentBuilder addLine(String line) {
        addSpaces();
        current.append(line);
        newLine();

        return this;
    }

    public FileContentBuilder addContent(FileContent content) {
        content.getLines().forEach(this::addLine);
        return this;
    }

    public FileContentBuilder addIndent(int spaces) {
        this.spaces += spaces;
        return this;
    }

    public FileContentBuilder removeIndent(int spaces) {
        this.spaces -= spaces;
        return this;
    }

    public FileContent build() {
        return new FileContent(current.toString());
    }

    private void addSpaces() {
        current.append(" ".repeat(spaces));
    }

    private void newLine() {
        current.append("\n");
    }
}
