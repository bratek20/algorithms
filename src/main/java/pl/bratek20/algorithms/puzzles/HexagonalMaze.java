package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.utils.Pair;

import java.util.LinkedList;
import java.util.Queue;

// https://www.codingame.com/ide/puzzle/hexagonal-maze
public class HexagonalMaze extends Puzzle {
    int w, h;
    char[][] maze;
    int [][] dist;
    boolean [][] shortestPath;

    void read() {
        var p = in.readIntPair();
        w = p.getLeft();
        h = p.getRight();
        maze = new char[h][w];
        dist = new int[h][w];
        shortestPath = new boolean[h][w];

        for (int i = 0; i < h; i++) {
            maze[i] = in.readLine().toCharArray();
            for (int j = 0; j < w; j++) {
                dist[i][j] = Integer.MAX_VALUE;
                shortestPath[i][j] = false;
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
                if ('S' == maze[i][j]) {
                    queue.add(new Pair(i, j));
                    dist[i][j] = 0;
                }
            }
        }

        while (!queue.isEmpty()) {
            var p = queue.poll();
            var i = p.getLeft();
            var j = p.getRight();

            int[] di = getDi(i);
            int[] dj = getDj(i);
            for (int k = 0; k < 6; k++) {
                int newI = fixI(i + di[k]);
                int newJ = fixJ(j + dj[k]);

                if (maze[newI][newJ] == '#') {
                    continue;
                }
                if (dist[newI][newJ] > dist[i][j] + 1) {
                    dist[newI][newJ] = dist[i][j] + 1;
                    queue.add(new Pair(newI, newJ));
                }
            }
        }
    }

    int[] getDi(int i) {
        return new int[] {-1, -1, 0, 0, 1, 1};
    }

    int[] getDj(int i) {
        if (i%2 == 0) {
            return new int[] {-1, 0, -1, 1, -1, 0};
        }
        else {
            return new int[] {0, 1, -1, 1, 0, 1};
        }
    }

    void calcShortestPath() {
        Queue<Pair> queue = new LinkedList<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if ('E' == maze[i][j]) {
                    queue.add(new Pair(i, j));
                    shortestPath[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            var p = queue.poll();
            var i = p.getLeft();
            var j = p.getRight();

            int[] di = getDi(i);
            int[] dj = getDj(i);
            for (int k = 0; k < 6; k++) {
                int newI = fixI(i + di[k]);
                int newJ = fixJ(j + dj[k]);

                if (maze[newI][newJ] == '#') {
                    continue;
                }
                if (dist[newI][newJ] == dist[i][j] - 1) {
                    shortestPath[newI][newJ] = true;
                    queue.add(new Pair(newI, newJ));
                }
            }
        }
    }

    void write() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (maze[i][j] == '#' || maze[i][j] == 'S' || maze[i][j] == 'E') {
                    out.print(maze[i][j]);
                } else if (shortestPath[i][j]) {
                    out.print('.');
                }
                else {
                    out.print('_');
                }
            }
            out.println();
        }
    }

    @Override
    public void solve() {
        read();

        calcDist();

        calcShortestPath();

        write();
    }
}
