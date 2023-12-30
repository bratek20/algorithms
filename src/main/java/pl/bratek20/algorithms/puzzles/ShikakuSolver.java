package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.array.d1.Array;
import pl.bratek20.algorithms.common.array.d2.*;
import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.utils.IntVariable;
import pl.bratek20.algorithms.common.utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

// https://www.codingame.com/ide/puzzle/shikaku-solver
public class ShikakuSolver extends Puzzle {
    Array2D<Integer> arr;
    ArrayList<Array2DPoint> points = new ArrayList<>();
    TreeMap<String, Array2D<Character>> solutions = new TreeMap<>();
    Array2D<Integer> solution;

    char getChar(int value) {
        return (char) ('A' + value);
    }

    Array2D<Character> getSolutionStr() {
        var result = new CharArray2D(solution.getColumns(), solution.getRows(), null);
        Array<Character> mapping = new Array<>(points.size() + 1, null);
        IntVariable x = new IntVariable();

        solution.forEach(cell -> {
            var point = cell.getPoint();
            var value = cell.getValue();
            if (mapping.get(value) == null) {
                mapping.set(value, getChar(x.get()));
                x.increment();
            }
            result.set(point, mapping.get(value));
        });

        return result;
    }

    void populate(int pointIdx) {
        if (pointIdx == points.size()) {
            var solutionStr = getSolutionStr();
            var flattened = solutionStr.flatByRows();
            var flattenedStr = new StringBuilder();
            flattened.forEach(cell -> flattenedStr.append(cell.getValue()));
            solutions.put(flattenedStr.toString(), solutionStr);
            return;
        }

        var point = points.get(pointIdx);
        int value = arr.get(point);
        allPairs(value).forEach(pair -> {
            populateForPair(pointIdx, pair);
        });
    }

    List<Pair> allPairs(int value) {
        var pairs = new ArrayList<Pair>();
        for (int i = 1; i <= value; i++) {
            if (value % i == 0) {
                pairs.add(new Pair(i, value / i));
            }
        }
        return pairs;
    }

    void populateForPair(int pointIdx, Pair pair) {
        var size = new Array2DPoint(pair.getLeft() - 1, pair.getRight() - 1);
        allStartPoints(pointIdx, pair).forEach(startPoint -> {
            var endPoint = startPoint.add(size);
            if (fits(startPoint, endPoint)) {
                fill(startPoint, endPoint, pointIdx);
                populate(pointIdx + 1);
                unfill(startPoint, endPoint);
            }
        });
    }

    boolean fits(Array2DPoint startPoint, Array2DPoint endPoint) {
        if (!solution.isInside(startPoint) || !solution.isInside(endPoint)) {
            return false;
        }

        for (int i = startPoint.row(); i <= endPoint.row(); i++) {
            for (int j = startPoint.column(); j <= endPoint.column(); j++) {
                if (solution.get(i, j) != null) {
                    return false;
                }
            }
        }
        return true;
    }

    void fill(Array2DPoint startPoint, Array2DPoint endPoint, int pointIdx) {
        solution.fillRectangle(startPoint, endPoint, pointIdx);
    }

    void unfill(Array2DPoint startPoint, Array2DPoint endPoint) {
        solution.fillRectangle(startPoint, endPoint, null);
    }

    List<Array2DPoint> allStartPoints(int pointIdx, Pair pair) {
        var point = points.get(pointIdx);
        var size = new Array2DPoint(pair.getLeft() - 1,  pair.getRight() - 1);
        var firstStart = point.subtract(size);

        List<Array2DPoint> startPoints = new ArrayList<>();
        for (int i = firstStart.row(); i <= point.row(); i++) {
            for (int j = firstStart.column(); j <= point.column(); j++) {
                startPoints.add(new Array2DPoint(i, j));
            }
        }
        return startPoints;
    }

    @Override
    public void solve() {
        arr = Array2DReader.readIntRectangle(in);
        arr.forEach(cell -> {
            if (cell.getValue() != 0) {
                points.add(cell.getPoint());
            }
        });

        solution = new Array2D<>(arr.getColumns(), arr.getRows(), null);
        populate(0);

        out.println(solutions.size());
        Array2DWriter.writeChar(out, solutions.firstEntry().getValue());
    }
}
