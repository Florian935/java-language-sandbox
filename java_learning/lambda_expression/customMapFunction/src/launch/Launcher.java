package launch;

public class Launcher {

    public static void main(String[] args) {
        Container<User> container = new Container<>();
        container.set(new User("Toto"));
        Admin admin = container.map(user -> new Admin(user.getPrenom(), "mypass"));
        ConnectedUser connectedUser = container.map(user -> new ConnectedUser(user.getPrenom(), "Tintin"));

        System.out.println(admin.getPassword());
        System.out.println(connectedUser.getNom());
    }
}
