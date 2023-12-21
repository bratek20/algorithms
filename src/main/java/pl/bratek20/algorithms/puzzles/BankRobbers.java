package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.input.Input;
import pl.bratek20.algorithms.common.puzzle.Puzzle;
import pl.bratek20.algorithms.common.utils.Pair;

import java.util.ArrayList;
import java.util.List;

// TODO puzzle URL
public class BankRobbers extends Puzzle {
    Pair findMin(List<Integer> times, int from, int to) {
        int min = times.get(from);
        int pos = from;
        for (int i = from; i < to; i++) {
            if (times.get(i) < min) {
                min = times.get(i);
                pos = i;
            }
        }
        return new Pair(min, pos);
    }

    int subtract(List<Integer> times, int from, int to, int value) {
        for (int i = from; i < to; i++) {
            times.set(i, times.get(i) - value);
        }
        return value;
    }

    void swap(List<Integer> times, int from, int to) {
        int tmp = times.get(from);
        times.set(from, times.get(to));
        times.set(to, tmp);
    }

    void doN(int n, Runnable runnable) {
        for (int i = 0; i < n; i++) {
            runnable.run();
        }
    }

    @Override
    public void solve() {
        int r = in.readInt();
        int v = in.readInt();
        var inTimes = in.readLines(v, Input::readIntPair)
            .stream()
            .map(pair -> {
                int c = pair.getLeft();
                int n = pair.getRight();
                return (int) (Math.pow(10, n) * Math.pow(5, c-n));
            })
            .toList();
        var times = new ArrayList<>(inTimes);

        doN(r, () -> times.add(0));

        int result = 0;
        for (int i = 0; i < v; i++) {
            var x = findMin(times, i, i + r);
            int min = x.getLeft();
            int pos = x.getRight();

            result += min;
            subtract(times, pos, i + r, min);
            swap(times, i, pos);
        }

        out.println(result);
    }
}
