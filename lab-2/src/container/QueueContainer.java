package container;

import container.Container;
import task.Task;

import java.util.ArrayList;

public class QueueContainer extends AbstractContainer {
    public QueueContainer() {
        this.elems = new ArrayList<>();
    }

    @Override
    public Task remove() {
        return elems.remove(0);
    }
}
