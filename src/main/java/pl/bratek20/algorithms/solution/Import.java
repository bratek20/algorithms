package pl.bratek20.algorithms.solution;

import lombok.Value;

@Value
public class Import {
    String value;

    public String getClassName() {
        var names = getPath().split("\\.");
        return names[names.length - 1];
    }

    public String getPath() {
        return value.replace("import ", "").replace(";", "");
    }
}
