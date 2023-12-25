package pl.bratek20.algorithms.puzzles;

import pl.bratek20.algorithms.common.array.d2.Array2DPoint;
import pl.bratek20.algorithms.common.array.d2.CharArray2D;
import pl.bratek20.algorithms.common.input.Input;
import pl.bratek20.algorithms.common.puzzle.Puzzle;

import java.util.HashSet;
import java.util.TreeSet;

// https://www.codingame.com/ide/puzzle/shoot-enemy-aircraft
public class ShootEnemyAircraft extends Puzzle {
    @Override
    public void solve() {
        int n = in.readInt();
        var lines = in.readLines(n, Input::readLine);
        var array = new CharArray2D(lines);
        var shootPoint = array.find(c -> c == '^').get();
        var turns = new TreeSet<Integer>();
        array.findAll(c -> c == '>' || c == '<').forEach(point -> {
            var turn = calcTurn(shootPoint, point);
            turns.add(turn);
        });

        for(int turn = 1; turn <= turns.last(); turn++) {
            if (turns.contains(turn)) {
                out.println("SHOOT");
            }
            else {
                out.println("WAIT");
            }
        }
    }

    int calcTurn(Array2DPoint shootPoint, Array2DPoint aircraft) {
        int turn = Math.abs(shootPoint.column() - aircraft.column());
        return turn - Math.abs(shootPoint.row() - aircraft.row());
    }
}
