package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.puzzle.Puzzle;

// https://www.codingame.com/ide/puzzle/abcdefghijklmnopqrstuvwxyz
public class AtoZ extends Puzzle {
    int n;
    char[][] grid;

    enum State {
        UNKNOWN,
        OK,
        FAIL
    }
    State[][] states;

    void read() {
        n = in.readInt();
        grid = new char[n][n];
        states = new State[n][n];
        for (int i = 0; i < n; i++) {
            grid[i] = in.readLine().toCharArray();
        }
    }

    boolean go(int i, int j) {
        return false;
    }

    void startGo() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'a') {
                    if(go(i, j)) {
                        return;
                    }
                }
            }
        }
    }

    void write() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (states[i][j] == State.OK) {
                    out.print(grid[i][j]);
                } else {
                    out.print('-');
                }
            }
            out.println();
        }
    }

    @Override
    public void solve() {
        read();

        startGo();

        write();
    }
}
