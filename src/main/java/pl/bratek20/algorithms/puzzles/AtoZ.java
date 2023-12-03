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
            for (int j = 0; j < n; j++) {
                states[i][j] = State.UNKNOWN;
            }
        }
    }

    boolean tryGo(int i, int j, int x) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            return false;
        }
        if (grid[i][j] != 'a' + x) {
            return false;
        }
        if (states[i][j] != State.UNKNOWN) {
            return states[i][j] == State.OK;
        }
        return go(i, j, x);
    }

    boolean go(int i, int j, int x) {
        if (grid[i][j] == 'z') {
            states[i][j] = State.OK;
            return true;
        }
        var res = tryGo(i + 1, j, x + 1)
            || tryGo(i - 1, j, x + 1)
            || tryGo(i, j + 1, x + 1)
            || tryGo(i, j - 1, x + 1);

        if (res) {
            states[i][j] = State.OK;
        } else {
            states[i][j] = State.FAIL;
        }
        return res;
    }

    void startGo() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'a') {
                    if(go(i, j, 0)) {
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
