package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.array.Array;
import pl.bratek20.algorithms.common.array2d.Array2D;
import pl.bratek20.algorithms.common.array2d.Array2DPoint;
import pl.bratek20.algorithms.common.array2d.Array2DReader;
import pl.bratek20.algorithms.common.bfs.BFS;
import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.utils.Variable;

import java.util.*;

// https://www.codingame.com/training/easy/longest-coast
public class LongestCoast extends Puzzle implements BFS.Strategy<Array2DPoint> {
    char WATER = '~';
    char LAND = '#';

    Array2D<Character> map;
    Array2D<Integer> indexes;

    @Override
    public List<Array2DPoint> getNeighbours(Array2DPoint node) {
        var i = node.row();
        var j = node.column();

        int[] di = new int[] {1, -1, 0, 0};
        int[] dj = new int[] {0, 0, 1, -1};
        List<Array2DPoint> neighbours = new LinkedList<>();
        for (int k = 0; k < 4; k++) {
            var newP = new Array2DPoint(i + di[k], j + dj[k]);
            if (map.isInside(newP) && map.get(newP) == LAND) {
                neighbours.add(newP);
            }
        }
        return neighbours;
    }


    @Override
    public void solve() {
        map = Array2DReader.readCharSquare(in);

        indexes = new Array2D<>(map.getRows(), map.getColumns(), -1);
        final var nextIndex = new Variable<>(1);
        map.forEach(cell -> {
            if (cell.getValue() == LAND && indexes.get(cell.getPoint()) == -1) {
                var bfs = new BFS<>(this);
                bfs.run(cell.getPoint());

                bfs.getVisited().forEach(p2 -> indexes.set(p2, nextIndex.get()));
                nextIndex.set(nextIndex.get() + 1);
            }
        });

        Array<Integer> coastLengths = new Array<>(nextIndex.get(), 0);
        map.forEach(cell -> {
            if (cell.getValue() == WATER) {
                int[] di = new int[] {1, -1, 0, 0};
                int[] dj = new int[] {0, 0, 1, -1};
                Set<Integer> islands = new HashSet<>();
                for (int k = 0; k < 4; k++) {
                    var newP = new Array2DPoint(cell.getPoint().row() + di[k], cell.getPoint().column() + dj[k]);
                    if (map.isInside(newP) && map.get(newP) == LAND) {
                        islands.add(indexes.get(newP));
                    }
                }
                islands.forEach(i -> coastLengths.set(i, coastLengths.get(i) + 1));
            }
        });

        final var max = new Variable<>(0);
        final var maxIndex = new Variable<>(1);
        coastLengths.forEach((length, index) -> {
            if (length > max.get()) {
                max.set(length);
                maxIndex.set(index);
            }
        });
        out.print(maxIndex + " " + max);
    }
}
