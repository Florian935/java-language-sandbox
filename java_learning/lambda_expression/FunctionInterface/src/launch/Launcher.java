package launch;

import model.Etudiant;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Launcher {
    public static void main(String[] args) {
        Etudiant e1 = new Etudiant("TOTO1", "Toto1", 20);
        Etudiant e2 = new Etudiant("TOTO2", "Toto2", 20);
        List<Etudiant> lesEtudiants = new ArrayList<>();
        lesEtudiants.add(e1);
        lesEtudiants.add(e2);

        // ---------- CONSUMER ---------
        // Consumer: un consumer représente une opération effectuée sur un unique paramètre en entrée.
        // Un consumer ne retourne aucun résultat, il effectue seulement un traitement.
        // Déclaration d'un consumer: on définit l'opération à effectuer sur le paramètre fourni.
        Consumer<Etudiant> etudiantConsumer = e -> System.out.println(e.getNom());
        // méthode accept: effectue l'opération définit sur l'objet passé en paramètre.
        // Donc ici, on passe un étudiant en paramètre et on effectue l'opération définit du consumer sur ce dernier (donc
        // un System.out.println du nom de l'étudiant).
        etudiantConsumer.accept(e1);

        // Le forEach de java fonctionne avec un consumer. J'ai ici recréé un forEach moi-même: il prend une liste d'étudiant
        // en premier paramètre. En second paramètre, un écrit l'opération à effectuer sur le paramètre d'entré.
        // Le contenu de mon forEach est ensuite de boucler sur la liste d'étudiant en appelant la fonction 'accept' sur
        // le consumer déclaré en paramètre (ici un System.out.println("YO")).
        e1.forEach(lesEtudiants, e -> System.out.println("YO"));


        // -------- FUNCTION ---------
        // Function: une function représente une opération effectuée sur un unique paramètre en entrée et retourne le
        // résultat de cette opération (principale différence avec un Consumer).

        // 2 notations différentes:
        // Cette première notation est à utiliser quand on a besoin de passer des arguments.
        // Ici, on passe en premier paramètre un étudiant et en second paramètre on décrit l'opération à effectuer sur
        // l'entrée fournit et le résultat à retourner par la function.
        // apply: la méthode apply applique, sur l'argument qui lui est passé en paramètre, la fonction sur laquelle
        // elle est appelée.
        e1.getInformationAboutEtudiant(e1, e -> e.getNomPrenom("Paris"));

        // Cette annotation est une référence de méthode qui permet d'alléger le code
        e1.getInformationAboutEtudiant(e1, Etudiant::getAge);

        // La notation ci-dessus est équivalente à celle-ci:
        e1.getInformationAboutEtudiant(e1, e -> e.getAge());

        // Ici, on déclare 2 functions. Elle décrive le type d'objet qui doit être fournie en paramètre d'entrée et le
        // résultat qui sera produit. A noter que les 2 functions fournissent un résultat identique.
        Function<Etudiant, String> etudiantFunctionClassic = e -> e.getPrenom();
        Function<Etudiant, String> etudiantFunctionReference = Etudiant::getPrenom;
        // Ici, on indique qu'il faut appliquer, sur l'objet e1, la function déclarée etudiantFunctionClassic et
        // etudiantFunctionReference.
        etudiantFunctionClassic.apply(e1);
        etudiantFunctionReference.apply(e1);

        // Pour aller plus loin: on peut même indiquer des traitements à effectuer: ici notre function retournera ainsi
        // un concaténation du prenom + de la chaine de caractère "Toto".
        Function<Etudiant, String> etudiantFunctionTraitement = e -> {
            String t = "Toto";
            e.setPrenom(e.getPrenom() + " " + t);
            return e.getPrenom();
        };
        etudiantFunctionTraitement.apply(e1);


        // Les Function sont un concept très puissant de part leur grande généricité. Ainsi, on peut déclarer une méthode
        // qui déclare une function dans son paramètre d'appel. Le premier paramètre de la function déclare le type attendu
        // sur lequel on devra effectuer une opération et retourner le résultat de l'opération. Le second paramètre est
        // généralement déclaré de façon générique. Ainsi, on pourra ensuite appeler la méthode en fournissant en
        // paramètre une Function qui fourni une opération en fonction des besoins ce qui permet une grande généricité
        // et une grande liberté dans le code.


        // Exemple: commençons par déclarer une méthode qui prend en premier paramètre un étudiant et en second paramètre
        // une Function qui attend en entrée un Objet de type Etudiant et qui retourne un résultat de type T (générique).
        // La fonction en second paramètre permettra ainsi d'effectuer une opération et fournir le résultat souhaité en
        // fonction de l'appel de cette méthode.
        // Pour fournir ce résultat, on appelle tout simplement la méthode apply qui permet d'effectuer sur l'objet "etudiant"
        // la Function fournit en paramètre.
        // public <T> T etudiantFunction(Etudiant etudiant, Function<Etudiant, T> functionResolver) {
        //      functionResolver.apply(etudiant);
        // }

        // Ensuite, il suffit d'appeler cette méthode en spécifiant l'opération que l'on souhaite effectuer sur notre etudiant:
        // Etudiant etudiant = new Etudiant("Tintin", "Toto", 20);
        // String prenom = etudiantFunction(etudiant, Etudiant::getPrenom);
        // String nom = etudiantFunction(etudiant, Etudiant::getNom);

        // Ainsi, ici, en fonction du besoin et grâce à la grande généricité des Function, on peut ainsi indiquer directement
        // l'opération qu'on souhaite effectuer en paramètre et on obtient le résultat. L'opération fourni en paramètre est
        // appliquée sur l'objet etudiant grâce à la méthode apply.
    }
}
