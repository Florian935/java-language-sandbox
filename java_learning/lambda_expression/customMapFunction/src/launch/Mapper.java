package launch;

@FunctionalInterface
public interface Mapper<T, R> {
    R apply(T valueToMap);
}
