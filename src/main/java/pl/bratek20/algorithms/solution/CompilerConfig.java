package pl.bratek20.algorithms.solution;

import java.util.List;
import java.util.Optional;

public class CompilerConfig {
    public static class Builder {
        private String modulesFolderPath;
        private Boolean attachMain;
        private List<String> compileImports;

        public Builder modulesFolderPath(String modulesFolderPath) {
            this.modulesFolderPath = modulesFolderPath;
            return this;
        }

        public Builder attachMain(boolean attachMain) {
            this.attachMain = attachMain;
            return this;
        }

        public Builder compileImports(String ...compileImports) {
            this.compileImports = List.of(compileImports);
            return this;
        }

        public CompilerConfig build() {
            var resolvedModulesFolderPath = Optional.ofNullable(this.modulesFolderPath)
                .orElseThrow(() -> new RuntimeException("Modules folder path is required"));
            var resolvedAttachMain = Optional.ofNullable(this.attachMain).orElse(false);
            var resolvedCompileImports = Optional.ofNullable(this.compileImports).orElse(List.of());

            return new CompilerConfig(
                resolvedModulesFolderPath,
                resolvedAttachMain,
                resolvedCompileImports
            );
        }
    }

    final String modulesFolderPath;
    final boolean attachMain;
    final List<String> compileImports;

    public CompilerConfig(String modulesFolderPath, boolean attachMain, List<String> compileImports) {
        this.modulesFolderPath = modulesFolderPath;
        this.attachMain = attachMain;
        this.compileImports = compileImports;
    }
}
