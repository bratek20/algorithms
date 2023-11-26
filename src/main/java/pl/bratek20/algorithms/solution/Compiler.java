package pl.bratek20.algorithms.solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class Compiler {
    private final String modulesFolderPath;
    public Compiler(String modulesFolderPath) {
        this.modulesFolderPath = modulesFolderPath;
    }

    public String compile(String puzzleName) {
        String rootPath = Paths.get("").toAbsolutePath().toString();

        String filePath = rootPath + "/" + modulesFolderPath + "/puzzles/" + puzzleName + ".java";
        StringBuilder stringBuilder = new StringBuilder();

        try {
            File file = new File(filePath);

            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }

                br.close();
                return stringBuilder.toString();
            } else {
                return "File not found: " + filePath;
            }
        } catch (IOException e) {
            return "Error reading file: " + e.getMessage();
        }
    }
}
