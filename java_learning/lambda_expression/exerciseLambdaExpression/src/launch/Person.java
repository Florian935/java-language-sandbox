package launch;

public class Person {

    private final String prenom;
    private final String nom;
    private int age;

    public Person(String prenom, String nom, int age) {
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    public Integer setAge(int age) {
        this.age = age;
        return this.age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", age=" + age +
                '}';
    }
}
