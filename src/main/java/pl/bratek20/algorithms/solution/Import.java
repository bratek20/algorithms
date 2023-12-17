package pl.bratek20.algorithms.solution;

import lombok.Value;

@Value
public class Import {
    String value;

    public String getClassName() {
        var names = getPath().split("\\.");
        return names[names.length - 1];
    }

    public String getValue() {
        return value;
    }

    public String getPath() {
        return value.replace("import ", "").replace(";", "");
    }

    public String getFilePath(String prefix) {
        return prefix + getPath().replace(".", "/") + ".java";
    }

    public String getPackagePath() {
        return getPath().substring(0, getPath().lastIndexOf("."));
    }

    public String getPackageFilePath(String prefix) {
        return prefix + getPackagePath().replace(".", "/");
    }

    public boolean isExternal() {
        return !getPath().startsWith("pl.bratek20.algorithms");
    }
}
