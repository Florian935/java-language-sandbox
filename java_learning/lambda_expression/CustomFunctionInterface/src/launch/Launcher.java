package launch;

public class Launcher {

    public void greeter(Greeting greeting) {
        greeting.perform();
    }

    public static void main(String[] args) {
        Greeting greeter = new Greeter();
        Launcher launcher = new Launcher();

        Greeting lambdaGreeting = () -> System.out.println("Hello World (lambda expression)");

        Greeting innerClass = new Greeting() {
            @Override
            public void perform() {
                System.out.println("Hello World (inner class)");
            }
        };

        launcher.greeter(greeter);
        lambdaGreeting.perform();
        launcher.greeter(lambdaGreeting);
        launcher.greeter(innerClass);
    }
}
