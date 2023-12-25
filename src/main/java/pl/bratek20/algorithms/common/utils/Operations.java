package pl.bratek20.algorithms.common.utils;

public class Operations {
    public static void doN(int n, Runnable runnable) {
        for (int i = 0; i < n; i++) {
            runnable.run();
        }
    }
}
