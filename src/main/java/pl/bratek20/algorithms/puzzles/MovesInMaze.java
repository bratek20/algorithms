package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.array2d.Array2D;
import pl.bratek20.algorithms.common.array2d.Array2DReader;
import pl.bratek20.algorithms.common.bfs.BFS;
import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.utils.Pair;

import java.util.LinkedList;
import java.util.List;

// https://www.codingame.com/ide/puzzle/moves-in-maze
public class MovesInMaze extends Puzzle implements BFS.Strategy<Pair> {
    Array2D<Character> maze;
    BFS<Pair> bfs;

    @Override
    public List<Pair> getNeighbours(Pair node) {
        var i = node.getLeft();
        var j = node.getRight();

        int[] di = new int[] {1, -1, 0, 0};
        int[] dj = new int[] {0, 0, 1, -1};
        List<Pair> neighbours = new LinkedList<>();
        for (int k = 0; k < 4; k++) {
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

    void calcDist() {
        Pair start = maze.find(c -> c == 'S').orElseThrow();
        bfs.run(start);
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
        maze.forEach((c, p) -> {
            if (c == '#') {
                out.print('#');
            } else {
                var dist = bfs.getDist(p);
                out.print(distToChar(dist));
            }
            if (p.getRight() == maze.getWidth() - 1) {
                out.println();
            }
        });
    }

    @Override
    public void solve() {
        read();

        calcDist();

        write();
    }
}
