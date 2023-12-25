package pl.bratek20.algorithms.solution.executor;

import lombok.SneakyThrows;
import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.puzzle.PuzzleSolver;
import pl.bratek20.algorithms.puzzles.RetroTypewriterArt;

public class Executor {

    public void execute(String puzzleName) {
        var puzzle = createPuzzle(puzzleName);
        var solver = new PuzzleSolver();
        solver.solve(puzzle);
    }

    @SneakyThrows
    private Puzzle createPuzzle(String puzzleName) {
        String className = "pl.bratek20.algorithms.puzzles." + puzzleName;

        // Load the class dynamically
        Class<?> dynamicClass = Class.forName(className);

        // Create an instance of the class
        Object instance = dynamicClass.getDeclaredConstructor().newInstance();

        return (Puzzle) instance;
    }
}
