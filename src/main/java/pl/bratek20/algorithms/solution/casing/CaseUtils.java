package pl.bratek20.algorithms.solution.casing;

public class CaseUtils {
    public static String kebabToPascal(String kebabCase) {
        StringBuilder pascalCase = new StringBuilder();

        // Split the input string by hyphen
        String[] words = kebabCase.split("-");

        // Capitalize the first letter of each word and append to the result
        for (String word : words) {
            pascalCase.append(capitalize(word));
        }

        return pascalCase.toString();
    }

    private static String capitalize(String str) {
        // Check if the string is not empty
        if (str != null && !str.isEmpty()) {
            // Capitalize the first letter and append the rest of the string
            return Character.toUpperCase(str.charAt(0)) + str.substring(1);
        } else {
            return str;
        }
    }
}
