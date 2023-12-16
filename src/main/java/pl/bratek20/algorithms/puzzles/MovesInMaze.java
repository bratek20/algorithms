package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.array2d.Array2D;
import pl.bratek20.algorithms.common.array2d.Array2DReader;
import pl.bratek20.algorithms.common.bfs.BFS;
import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.utils.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://www.codingame.com/ide/puzzle/moves-in-maze
public class MovesInMaze extends Puzzle {
    int w, h;
    Array2D<Character> maze;
    Array2D<Integer> dist;
    BFS<Pair> bfs;

    void read() {
        maze = Array2DReader.readChar(in);
        w = maze.getWidth();
        h = maze.getHeight();
        dist = new Array2D<>(w, h, Integer.MAX_VALUE);

        bfs = new BFS<>(new BFS.Strategy<>() {
            @Override
            public List<Pair> getNeighbours(Pair node) {
                var i = node.getLeft();
                var j = node.getRight();

                int[] di = new int[] {1, -1, 0, 0};
                int[] dj = new int[] {0, 0, 1, -1};
                List<Pair> neighbours = new LinkedList<>();
                for (int k = 0; k < 4; k++) {
                    int newI = fixI(i + di[k]);
                    int newJ = fixJ(j + dj[k]);

                    if (maze.get(newI, newJ) == '#') {
                        continue;
                    }
                    neighbours.add(new Pair(newI, newJ));
                }
                return neighbours;
            }
        });
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
        Pair start = null;
        for (int i = 0; i < h && start == null; i++) {
            for (int j = 0; j < w && start == null; j++) {
                if (maze.get(i, j) == 'S') {
                    start = new Pair(i, j);
                }
            }
        }

        bfs.run(start);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                dist.set(i, j, bfs.getDist(new Pair(i, j)));
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
