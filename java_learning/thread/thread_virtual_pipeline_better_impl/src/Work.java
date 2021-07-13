public class Work {

    private Result<Integer> result;
    private int stageNumber;

    public Result<Integer> processStage(Result<Integer> result, int stageNumber) {
        this.result = result;
        this.stageNumber = stageNumber;

        switch (stageNumber) {
            case 0 -> processStage0();
            case 1 -> processStage1();
            case 2 -> processStage2();
        }
        sleepThreadForMillis(2000);

        return result;
    }

    private void sleepThreadForMillis(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            logError("Le thread a été interrompu");
        }
    }

    private void logError(String message) {
        System.err.println(message);
    }

    private void processStage0() {
        final String message = String.format(
                "Etage %d a reçu la valeur %d (Sérialisation)",
                stageNumber,
                result.getResult()
        );
        logResult(message);
        result.setResult(result.getResult() + 1);
    }

    private void processStage1() {
        final String message = String.format(
                "Etage %d a reçu la valeur %d (Connexion)",
                stageNumber,
                result.getResult()
        );
        logResult(message);
        result.setResult(result.getResult() + 1);
    }

    private void processStage2() {
        final String message = String.format(
                "Etage %d a reçu la valeur %d (Désérialisation)",
                stageNumber,
                result.getResult()
        );
        logResult(message);
        result.setResult(result.getResult() + 1);
    }

    private void logResult(String message) {
        System.out.println(message);
    }
}
