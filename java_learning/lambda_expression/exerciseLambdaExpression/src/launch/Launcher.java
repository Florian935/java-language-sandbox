package launch;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Launcher {

    public static void displayPersonsConditionally(PersonUtil personUtil, List<Person> persons) {
        personUtil.apply(persons);
    }

    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Toto", "TOTO", 30),
                new Person("Yaya", "YAYA", 40),
                new Person("Zozo", "ZOZO", 50),
                new Person("Tatc", "TATA", 60),
                new Person("Tatb", "TATA", 50),
                new Person("Tata", "TATA", 50)
        );

        System.out.println("Sorting persons list ...");
        PersonUtil printPersonsSortedBy = p -> p.sort(Comparator.comparing(Person::getNom)
                        .thenComparing(Person::getAge)
                        .thenComparing(Person::getPrenom)
        );
        displayPersonsConditionally(printPersonsSortedBy, persons);


        System.out.println("List sorted. Print each person of the list (sorted):");
        PersonUtil printEachPerson = p -> p.forEach(System.out::println);
        displayPersonsConditionally(printEachPerson, persons);

        System.out.println("Filter:");
        PersonUtil filterNom = p -> p
                .stream()
                .filter(pers -> pers.getNom().startsWith("T"))
                .forEach(System.out::println);
        displayPersonsConditionally(filterNom, persons);
    }
}
