package launch;

public class Room<T> {

    private T t;

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }
}
