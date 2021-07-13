package launch;

public class Launcher<T> {

    public <R> R greeter(Greeting<T, R> greeting, T paramToPerform) {
        return greeting.perfom(paramToPerform);
    }

    public static void main(String[] args) {
        Launcher<Etudiant> launcher1 = new Launcher<>();
        Launcher<Enseignant> launcher2 = new Launcher<>();
        Etudiant etudiant = new Etudiant("Flo", "Martin");
        Enseignant enseignant = new Enseignant("Flo", 3);

        Greeting<Etudiant, String> lambdaExpression1 = Etudiant::getPrenom;
        Greeting<Enseignant, Integer> lambdaExpression2 = Enseignant::getAge;


        System.out.println(lambdaExpression1.perfom(etudiant));
        System.out.println(lambdaExpression2.perfom(enseignant));

        System.out.println(launcher1.greeter(lambdaExpression1, etudiant));
        System.out.println(launcher2.greeter(lambdaExpression2, enseignant));
    }

}
