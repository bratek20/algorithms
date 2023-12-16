package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.array2d.Array2D;
import pl.bratek20.algorithms.common.array2d.Array2DReader;
import pl.bratek20.algorithms.common.array2d.Array2DWriter;
import pl.bratek20.algorithms.common.bfs.BFS;
import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.utils.Pair;

import java.util.LinkedList;
import java.util.List;

// https://www.codingame.com/ide/puzzle/hexagonal-maze
public class HexagonalMaze extends Puzzle implements BFS.Strategy<Pair> {
    Array2D<Character> maze;
    BFS<Pair> bfs;

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

    @Override
    public List<Pair> getNeighbours(Pair node) {
        var i = node.getLeft();
        var j = node.getRight();

        int[] di = getDi(i);
        int[] dj = getDj(i);
        List<Pair> neighbours = new LinkedList<>();
        for (int k = 0; k < 6; k++) {
            var newP = maze.fix(i + di[k], j + dj[k]);

            if (maze.get(newP) == '#') {
                continue;
            }
            neighbours.add(newP);
        }
        return neighbours;
    }

    void read() {
        maze = Array2DReader.readChar(in);
        bfs = new BFS<>(this);
    }

    void calcShortestPath() {
        Pair start = maze.find(c -> c == 'S').orElseThrow();
        bfs.run(start);

        Pair end = maze.find(c -> c == 'E').orElseThrow();
        bfs.getShortestPath(end).forEach(p -> {
            if (maze.get(p) == '_') {
                maze.set(p, '.');
            }
        });
    }

    void write() {
        Array2DWriter.writeChar(out, maze);
    }

    @Override
    public void solve() {
        read();

        calcShortestPath();

        write();
    }
}
