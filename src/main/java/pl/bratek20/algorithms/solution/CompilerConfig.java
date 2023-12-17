package pl.bratek20.algorithms.solution;

import java.util.List;
import java.util.Optional;

public class CompilerConfig {
    public static class Builder {
        private String basePath;
        private Boolean attachMain;
        private List<String> compileImports;
        private Boolean importWholePackage;

        public Builder basePath(String basePath) {
            this.basePath = basePath;
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

        public Builder importWholePackage(boolean importWholePackage) {
            this.importWholePackage = importWholePackage;
            return this;
        }

        public CompilerConfig build() {
            var resolvedBasePath = Optional.ofNullable(this.basePath)
                .orElseThrow(() -> new RuntimeException("Base path is required"));
            var resolvedAttachMain = Optional.ofNullable(this.attachMain).orElse(false);
            var resolvedCompileImports = Optional.ofNullable(this.compileImports).orElse(List.of());
            var resolvedImportWholePackage = Optional.ofNullable(this.importWholePackage).orElse(false);

            return new CompilerConfig(
                resolvedBasePath,
                resolvedAttachMain,
                resolvedCompileImports,
                resolvedImportWholePackage
            );
        }
    }

    final String basePath;
    final boolean attachMain;
    final List<String> compileImports;
    final boolean importWholePackage;

    public CompilerConfig(
        String basePath,
        boolean attachMain,
        List<String> compileImports,
        boolean importWholePackage
    ) {
        this.basePath = basePath;
        this.attachMain = attachMain;
        this.compileImports = compileImports;
        this.importWholePackage = importWholePackage;
    }
}