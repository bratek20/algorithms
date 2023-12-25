package pl.bratek20.algorithms.solution.executor;

import lombok.SneakyThrows;
import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleSolver;

public class Executor {
    private final String puzzlesPackage;
    private final PuzzleSolver solver;

    public Executor(String puzzlesPackage, PuzzleSolver solver) {
        this.puzzlesPackage = puzzlesPackage;
        this.solver = solver;
    }

    public void execute(String puzzleName) {
        var puzzle = createPuzzle(puzzleName);
        solver.solve(puzzle);
    }

    @SneakyThrows
    private Puzzle createPuzzle(String puzzleName) {
        String className = puzzlesPackage + "." + puzzleName;

        // Load the class dynamically
        Class<?> dynamicClass = Class.forName(className);

        // Create an instance of the class
        Object instance = dynamicClass.getDeclaredConstructor().newInstance();

        return (Puzzle) instance;
    }
}
