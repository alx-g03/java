package container;

import container.Container;
import task.Task;

import java.util.ArrayList;

public class StackContainer extends AbstractContainer {
    public StackContainer() {
        this.elems = new ArrayList<>();
    }

    @Override
    public Task remove() {
        int pos = elems.size() - 1;
        return elems.remove(pos);
    }
}
