package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.array2d.Array2D;
import pl.bratek20.algorithms.common.array2d.Array2DPoint;
import pl.bratek20.algorithms.common.array2d.Array2DReader;
import pl.bratek20.algorithms.common.array2d.Array2DWriter;
import pl.bratek20.algorithms.common.bfs.BFS;
import pl.bratek20.algorithms.common.puzzle.Puzzle;

import java.util.LinkedList;
import java.util.List;

// https://www.codingame.com/ide/puzzle/moves-in-maze
public class MovesInMaze extends Puzzle implements BFS.Strategy<Array2DPoint> {
    Array2D<Character> maze;
    BFS<Array2DPoint> bfs;

    @Override
    public List<Array2DPoint> getNeighbours(Array2DPoint node) {
        var i = node.row();
        var j = node.column();

        int[] di = new int[] {1, -1, 0, 0};
        int[] dj = new int[] {0, 0, 1, -1};
        List<Array2DPoint> neighbours = new LinkedList<>();
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
        maze = Array2DReader.readCharRectangle(in);
        bfs = new BFS<>(this);
    }

    void calcDist() {
        Array2DPoint start = maze.find(c -> c == 'S').orElseThrow();
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
        maze.forEach(cell -> {
            if (cell.getValue() != '#') {
                var dist = bfs.getDist(cell.getPoint());
                maze.set(cell.getPoint(), distToChar(dist));
            }
        });
        Array2DWriter.writeChar(out, maze);
    }

    @Override
    public void solve() {
        read();

        calcDist();

        write();
    }
}
