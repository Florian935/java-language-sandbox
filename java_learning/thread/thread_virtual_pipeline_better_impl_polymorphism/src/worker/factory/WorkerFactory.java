package worker.factory;

import worker.Worker;

public interface WorkerFactory {
    Worker makeWorker(int stageNumber);
}
