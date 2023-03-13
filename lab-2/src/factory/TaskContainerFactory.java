package factory;

import container.Container;
import container.QueueContainer;
import container.StackContainer;

public class TaskContainerFactory implements Factory {
    private static TaskContainerFactory instance = null;
    public final static TaskContainerFactory INSTANCE = new TaskContainerFactory();

    private TaskContainerFactory() {}

    public static Factory getInstance() {
        if (instance == null)
            instance = new TaskContainerFactory();
        return instance;
    }

    @Override
    public Container createContainer(Strategy strategy) {
        if (strategy==Strategy.LIFO)
            return new StackContainer();
        if (strategy==Strategy.FIFO)
            return new QueueContainer();
        else return null;
    }
}
