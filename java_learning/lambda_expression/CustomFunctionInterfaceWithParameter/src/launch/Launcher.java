package launch;

public class Launcher {

    public void greeter(Greeting greeting, int x, int y) {
        greeting.perform(x, y);
    }

    public static void main(String[] args) {
        final Launcher launcher = new Launcher();

        Greeting lambdaExpression = (x, y) -> System.out.println(x + y);

        Greeting lambdaExpressionWithBody = (x, y) -> {
            int result = x + y;
            System.out.println(result);
        };

        lambdaExpression.perform(2, 3);
        lambdaExpressionWithBody.perform(2, 3);
        launcher.greeter(lambdaExpression, 2, 3);
    }
}
