package pl.bratek20.algorithms.common.bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS<N> {
    public abstract static class Strategy<N2> {
        public abstract List<N2> getNeighbours(N2 node);
    }

    private final HashMap<N, Integer> dist = new HashMap<>();
    private final Strategy<N> strategy;

    public BFS(Strategy<N> strategy) {
        this.strategy = strategy;
    }

    public void run(N start) {
        Queue<N> queue = new LinkedList<>();

        queue.add(start);
        setDist(start, 0);

        while (!queue.isEmpty()) {
            var current = queue.poll();
            var neighbours = strategy.getNeighbours(current);
            neighbours.forEach(neighbour -> {
                if (getDist(neighbour) > getDist(current) + 1) {
                    setDist(neighbour, getDist(current) + 1);
                    queue.add(neighbour);
                }
            });
        }
    }

    public int getDist(N node) {
        return dist.getOrDefault(node, Integer.MAX_VALUE);
    }

    private void setDist(N node, int value) {
        dist.put(node, value);
    }
}
