package pl.bratek20.algorithms.common.bfs;

import java.util.*;

public class BFS<N> {

    public interface Strategy<N2> {
        List<N2> getNeighbours(N2 node);
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

    public List<N> getShortestPath(N from) {
        List<N> path = new LinkedList<>();
        path.add(from);
        while (getDist(from) != 0) {
            var neighbours = strategy.getNeighbours(from);
            for (var neighbour : neighbours) {
                if (getDist(neighbour) == getDist(from) - 1) {
                    path.add(neighbour);
                    from = neighbour;
                    break;
                }
            }
        }
        return path;
    }

    public List<N> getVisited() {
        return new ArrayList<>(dist.keySet());
    }
}
