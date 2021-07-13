package launch;

public class Launcher {

    public static void main(String[] args) {
        final Room<Student> room1 = new Room<>();
        final Room<Teacher> room2 = new Room<>();
        final Room<Boolean> room3 = new Room<>();
        room1.set(new Student("Flo"));
        room2.set(new Teacher(30));
        room3.set(Boolean.TRUE);

        System.out.println(room1.get().getPrenom());
        System.out.println(room2.get().getAge());
        System.out.println(room3.get().booleanValue());
    }
}
