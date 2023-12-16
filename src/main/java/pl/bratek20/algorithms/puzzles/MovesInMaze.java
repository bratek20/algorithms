package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.array2d.Array2D;
import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.utils.Pair;

import java.util.LinkedList;
import java.util.Queue;

// https://www.codingame.com/ide/puzzle/moves-in-maze
public class MovesInMaze extends Puzzle {
    int w, h;
    Array2D<Character> maze;
    Array2D<Integer> dist;

    void read() {
        var p = in.readIntPair();
        w = p.getLeft();
        h = p.getRight();
        maze = new Array2D<>(w, h, ' ');
        dist = new Array2D<>(w, h, Integer.MAX_VALUE);

        for (int i = 0; i < h; i++) {
            var charArray = in.readLine().toCharArray();
            for (int j = 0; j < w; j++) {
                maze.set(i, j, charArray[j]);
            }
        }
    }

    int fixI(int i) {
        if (i < 0) {
            return h - 1;
        }
        if (i >= h) {
            return 0;
        }
        return i;
    }

    int fixJ(int j) {
        if (j < 0) {
            return w - 1;
        }
        if (j >= w) {
            return 0;
        }
        return j;
    }

    void calcDist() {
        Queue<Pair> queue = new LinkedList<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if ('S' == maze.get(i, j)) {
                    queue.add(new Pair(i, j));
                    dist.set(i, j, 0);
                }
            }
        }

        while (!queue.isEmpty()) {
            var p = queue.poll();
            var i = p.getLeft();
            var j = p.getRight();

            int[] di = new int[] {1, -1, 0, 0};
            int[] dj = new int[] {0, 0, 1, -1};
            for (int k = 0; k < 4; k++) {
                int newI = fixI(i + di[k]);
                int newJ = fixJ(j + dj[k]);

                if (maze.get(newI, newJ) == '#') {
                    continue;
                }
                if (dist.get(newI, newJ) > dist.get(i, j) + 1) {
                    dist.set(newI, newJ, dist.get(i, j) + 1);
                    queue.add(new Pair(newI, newJ));
                }
            }
        }
    }

    char distToChar(int d) {
        if (d == Integer.MAX_VALUE) {
            return '.';
        }
        if (d < 10) {
            return (char) ('0' + d);
        }
        return (char) ('A' + d - 10);
    }

    void write() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (maze.get(i, j) == '#') {
                    out.print('#');
                } else {
                    out.print(distToChar(dist.get(i, j)));
                }
            }
            out.println();
        }
    }

    @Override
    public void solve() {
        read();

        calcDist();

        write();
    }
}
