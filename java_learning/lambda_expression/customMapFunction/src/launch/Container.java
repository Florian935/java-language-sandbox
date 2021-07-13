package launch;

public class Container<T> {

    private T value;

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }

    public <R> R map(Mapper<T, R> mapper) {
        return mapper.apply(value);
    }
}
