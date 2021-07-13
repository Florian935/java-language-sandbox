package launch;

@FunctionalInterface
public interface Greeting<T, R> {

    R perfom(T param);
}
