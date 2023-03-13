import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // task.Task t = new task.Task(1L, "task");
        // System.out.println(t);
        // System.out.println(t.getTaskID());
        // System.out.println(t.getDescriere());

         task.MessageTask m = new task.MessageTask(1L, "SMS", "Salut", "A.", "A.", LocalDateTime.now());
         m.execute();

         int[] arr1 = { 64, 34, 25, 12, 22, 11, 90 };
         task.SortingTask s1 = new task.SortingTask(1L, "mergeSort", arr1);
         s1.execute();

        int[] arr2 = { 2, 1, 4, 3, 10, 5, 9 };
        task.SortingTask s2 = new task.SortingTask(1L, "bubbleSort", arr2);
        s2.execute();
    }
}
