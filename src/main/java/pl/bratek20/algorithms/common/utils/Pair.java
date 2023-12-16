package pl.bratek20.algorithms.common.utils;

public class Pair {
    private final int left;
    private final int right;

    public Pair(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pair pair = (Pair) o;

        if (left != pair.left) {
            return false;
        }
        return right == pair.right;
    }

    @Override
    public int hashCode() {
        int result = left;
        result = 31 * result + right;
        return result;
    }
}
