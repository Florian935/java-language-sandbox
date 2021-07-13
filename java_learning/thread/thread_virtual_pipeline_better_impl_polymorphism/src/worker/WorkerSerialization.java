package worker;

import resolver.Result;

public class WorkerSerialization extends Worker {
    @Override
    public Result<Integer> processStage(Result<Integer> result, int stageNumber) {
        super.result = result;
        super.stageNumber = stageNumber;

        final String message = String.format(
                "Etage %d a reçu la valeur %d (Sérialisation)",
                stageNumber,
                result.getResult()
        );
        logResult(message);
        result.setResult(result.getResult() + 1);
        sleepThreadForMillis(2000);

        return result;
    }
}
