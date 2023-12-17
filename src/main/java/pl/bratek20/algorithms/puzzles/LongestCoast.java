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
        return node.neighbors()
                .stream()
                .filter(p -> map.isInside(p) && map.get(p) == LAND)
                .toList();
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
                Set<Integer> islands = new HashSet<>();
                cell.getPoint().neighbors().forEach(p -> {
                    if (map.isInside(p) && map.get(p) == LAND) {
                        islands.add(indexes.get(p));
                    }
                });
                islands.forEach(i -> coastLengths.set(i, coastLengths.get(i) + 1));
            }
        });

        final var max = new Variable<>(0);
        final var maxIndex = new Variable<>(1);
        coastLengths.forEach(cell -> {
            var index = cell.getIndex();
            var length = cell.getValue();

            if (length > max.get()) {
                max.set(length);
                maxIndex.set(index);
            }
        });
        out.print(maxIndex + " " + max);
    }
}
