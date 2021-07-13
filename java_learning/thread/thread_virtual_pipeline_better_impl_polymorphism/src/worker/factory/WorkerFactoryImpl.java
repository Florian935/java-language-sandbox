package worker.factory;

import worker.Worker;
import worker.WorkerConnection;
import worker.WorkerDeserialization;
import worker.WorkerSerialization;

public class WorkerFactoryImpl implements WorkerFactory{
    @Override
    public Worker makeWorker(int stageNumber) {
        return switch (stageNumber) {
            case 0 -> new WorkerSerialization();
            case 1 -> new WorkerConnection();
            case 2 -> new WorkerDeserialization();
            default -> null;
        };
    }
}
