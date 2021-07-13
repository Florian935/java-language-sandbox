package launch;

import java.util.function.Function;

public class Launcher<T> {

    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public static <G> G genericMethod(G genericParamToReturn) {
        return genericParamToReturn;
    }

    // Le "<A, B> permet de déclarer les types génériques que Java va déduire à l'appelle de la méthode.
    // Etant donné que les types sont déclarés au niveau de la méthode, la scope de ces types est limitée
    // au niveau de la méthode. Quand on déclare un type générique au niveau de la classe (comme ici notre classe
    // Launcher<T>), alors la scope est au niveau de la classe.
    public static <A, B> B genericMethod2(Function<A, B> lambdaToPerform, A paramToBePerformed) {
        return lambdaToPerform.apply(paramToBePerformed);
    }

    public static void main(String[] args) {
        Launcher<Student> launcher = new Launcher<>();
        launcher.set(new Student("Titi"));

        Student student1 = genericMethod(launcher.get());
        String string = genericMethod("Toto");
        Integer integer = genericMethod(10);
        Student student2 = genericMethod(new Student("Flo"));
        String prenom = genericMethod2(Student::getPrenom, new Student("Thierry"));
        Function<String, Integer> stringLengthLambda = String::length;
        Integer stringLength = genericMethod2(stringLengthLambda, "Hello");

        System.out.println(string);
        System.out.println(integer);
        System.out.println(student1.getPrenom());
        System.out.println(student2.getPrenom());
        System.out.println(prenom);
        System.out.println(stringLength);
    }
}
