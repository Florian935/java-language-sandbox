package launch;

public class Admin extends User {
    private final String password;

    public Admin(String prenom, String password) {
        super(prenom);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
