package worker;

import resolver.Result;

@FunctionalInterface
public interface Workeable {
    Result<Integer> processStage(Result<Integer> result, int stageNumber);
}
