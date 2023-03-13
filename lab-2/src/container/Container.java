package container;

import task.Task;

public interface Container {
    void add(Task task);
    Task remove();
    int size();
    boolean isEmpty();
}
