package task;

import task.Task;
import utils.Utils;

import java.util.Objects;

public class SortingTask extends Task {
    private int[] arr;

    public SortingTask(Long taskID, String descriere, int[] arr) {
        super(taskID, descriere);
        this.arr = arr;
    }

    private int[] bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        return arr;
    }

    private int[] mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            Utils.merge(arr, l, m, r);
        }
        return arr;
    }

    @Override
    public void execute() {
        if (Objects.equals(this.getDescriere(), "bubbleSort")) {
            Utils.printArray(bubbleSort(arr));
        }
        if (Objects.equals(this.getDescriere(), "mergeSort")) {
            Utils.printArray(mergeSort(arr, 0, arr.length - 1));
        }
    }
}
