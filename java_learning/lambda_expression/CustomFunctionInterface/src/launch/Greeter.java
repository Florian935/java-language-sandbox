package launch;

public class Greeter implements Greeting {
    @Override
    public void perform() {
        System.out.println("Hello World (Greeter class)");
    }
}
