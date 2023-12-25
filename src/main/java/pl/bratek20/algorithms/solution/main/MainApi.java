package pl.bratek20.algorithms.solution.main;

public interface MainApi {
    void compile(String puzzleName, boolean spyInput);
    void generate(String puzzleUrl);
    void execute(String puzzleName);
}
