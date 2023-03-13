package container;

import task.Task;

import java.util.List;

public abstract class AbstractContainer implements Container {
    List<Task> elems;

    @Override
    public void add(Task task) {
        elems.add(task);
    }

    @Override
    public abstract Task remove();

    @Override
    public int size() {
        return elems.size();
    }

    @Override
    public boolean isEmpty() {
        return elems.isEmpty();
    }
}
