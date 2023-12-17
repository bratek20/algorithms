package pl.bratek20.algorithms.common.array;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractArray<T, P, C extends AbstractCell<T, P>> {
    protected abstract List<C> getCells();
    protected abstract <NT, NC extends AbstractCell<NT, P>> AbstractArray<NT, P, NC> emptyCopy();

    public abstract T get(P point);
    public abstract void set(P point, T value);

    public void forEach(Consumer<C> consumer) {
        getCells().forEach(consumer);
    }

    public <NT, NC extends AbstractCell<NT, P>> AbstractArray<NT, P, NC> map(Function<C, NT> mapper) {
        AbstractArray<NT, P, NC> newArray = emptyCopy();
        forEach(cell -> newArray.set(cell.getPoint(), mapper.apply(cell)));
        return newArray;
    }
}
