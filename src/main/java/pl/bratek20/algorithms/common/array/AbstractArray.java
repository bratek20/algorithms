package pl.bratek20.algorithms.common.array;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractArray<
    T,
    P,
    C extends AbstractCell<T, P>,
    A extends AbstractArray<T, P, C, A>
> {
    protected abstract List<C> getCells();
    protected abstract A emptyCopy();

    public abstract T get(P point);
    public abstract void set(P point, T value);

    public void forEach(Consumer<C> consumer) {
        getCells().forEach(consumer);
    }

    public A map(Function<C, T> mapper) {
        A newArray = emptyCopy();
        forEach(cell -> newArray.set(cell.getPoint(), mapper.apply(cell)));
        return newArray;
    }

    public Optional<P> find(Predicate<T> predicate) {
        return getCells().stream()
            .filter(cell -> predicate.test(cell.getValue()))
            .map(C::getPoint)
            .findFirst();
    }
}
