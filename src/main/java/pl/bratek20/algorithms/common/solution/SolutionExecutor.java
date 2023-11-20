package pl.bratek20.algorithms.common.solution;

import pl.bratek20.algorithms.common.input.SystemInput;
import pl.bratek20.algorithms.common.output.SystemOutput;
import pl.bratek20.algorithms.puzzles.RetroTypewriterArt;

public class SolutionExecutor {
    public static void main(String[] args) {
        var solution = new RetroTypewriterArt();
        solution.init(new SystemInput(), new SystemOutput());
        solution.solve();
    }
}
