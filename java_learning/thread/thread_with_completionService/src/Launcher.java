import java.util.concurrent.*;

public class Launcher {
    private static final int NUMBER_TASK = 5;

    public static void main(String[] args) {
        final ExecutorService executorService =
                Executors.newFixedThreadPool(NUMBER_TASK);
        final CompletionService<Integer> completionService =
                new ExecutorCompletionService<>(executorService);

        for (int i = 0; i < NUMBER_TASK; i++) {
            completionService.submit(
                    new Task(i + 1, NUMBER_TASK - i));
        }

        for (int i = 0; i < NUMBER_TASK; i++) {
            Integer resultat;
            try {
                // Les invocations successives de la méthode take() renvoient
                // les instances de type Future au fur et à mesure de la fin de
                // l'exécution des tâches. Les résultats sont ainsi obtenus non
                // pas dans leur ordre de lancement mais dans l'ordre dans lequel
                // les tâches se terminent.

                // Attention : comme la méthode take() est bloquante, il ne faut
                // surtout pas l'invoquer un nombre de fois supérieur à celui
                // des tâches soumises car dans ce cas, le thread restera
                // bloqué indéfiniment.
                resultat = completionService.take().get();
                System.out.printf("Tache %d terminée\n", resultat);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // Après cette ligne, plus aucune tâche
        // ne sera acceptée par l'executor.
        executorService.shutdown();
    }
}
