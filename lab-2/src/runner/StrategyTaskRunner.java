package runner;

import container.Container;
import factory.Strategy;
import factory.TaskContainerFactory;
import task.Task;

public class StrategyTaskRunner implements TaskRunner {
    private Container container;

    public StrategyTaskRunner(Strategy strategy) {
        container = TaskContainerFactory.getInstance().createContainer(strategy);
    }

    @Override
    public void executeOneTask() {
        if (!container.isEmpty())
            container.remove().execute();
    }

    @Override
    public void executeAll() {
        while (hasTask())
            executeOneTask();
    }

    @Override
    public void addTask(Task task) {
        container.add(task);
    }

    @Override
    public boolean hasTask() {
        return !container.isEmpty();
    }
}
