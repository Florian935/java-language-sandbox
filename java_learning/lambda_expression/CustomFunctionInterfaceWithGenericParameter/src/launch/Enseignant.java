package launch;

public class Enseignant {

    private final String prenom;
    private final Integer age;

    public Enseignant(String prenom, Integer age) {
        this.prenom = prenom;
        this.age = age;
    }

    public String getPrenom() {
        return prenom;
    }

    public Integer getAge() {
        return age;
    }
}
