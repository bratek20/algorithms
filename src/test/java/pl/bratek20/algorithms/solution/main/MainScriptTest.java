package pl.bratek20.algorithms.solution.main;


import lombok.RequiredArgsConstructor;

public class MainScriptTest extends MainApiTest {

    @Override
    public Context createContext() {
        var implContext = new MainImplTest().createContext();
        var script = new MainScript(implContext.api());

        return new Context(
            new ScriptUser(script),
            implContext.executorMock(),
            implContext.generatorMock(),
            implContext.compilerMock(),
            implContext.clipboardMock()
        );
    }

    @RequiredArgsConstructor
    class ScriptUser implements MainApi {
        private final MainScript script;

        @Override
        public void compile(String puzzleName, boolean spyInput) {
            script.run(new String[]{"-c", puzzleName, spyInput ? "-spy" : ""});
        }

        @Override
        public void generate(String puzzleUrl) {
            script.run(new String[]{"-g", puzzleUrl});
        }

        @Override
        public void execute(String puzzleName) {
            script.run(new String[]{"-e", puzzleName});
        }
    }
}
