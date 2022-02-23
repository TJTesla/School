package com.company;

public class QuickSort {
    private static int[] sort(int[] arr, int iter) {
        QuickSort.printIter(arr, iter);

        if (arr.length <= 1) {
            return arr;
        }

        int pivot = arr[0];
        int[] small = new int[0];
        int[] large = new int[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= pivot) {
                small = Handler.push_back(small, arr[i]);
            } else {
                large = Handler.push_back(large, arr[i]);
            }
        }
        sort(small, iter+1);
        sort(large, iter +1);

        Handler.clear(arr);

        System.arraycopy(small, 0, arr, 0, small.length);
        arr[small.length] = pivot;
        System.arraycopy(large, 0, arr,small.length+1, large.length);

        QuickSort.printIter(arr, iter);

        return arr;
    }

    private static void printIter(int[] arr, int iter) {
        System.out.println(iter + ". Durchgang: ");
        System.out.print("Array: " );
        Handler.print(arr);

        System.out.println("");
    }

    public static int[] sort() {
        int[] arr = Handler.arr1;

        sort(arr, 1);

        Handler.print(arr);
        return arr;
    }

    public static int[] sort(int[] arr) {
        sortNoOut(arr);

        Handler.print(arr);
        return arr;
    }

    private static int[] sortNoOut(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int pivot = arr[0];
        int[] small = new int[0];
        int[] large = new int[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= pivot) {
                small = Handler.push_back(small, arr[i]);
            } else {
                large = Handler.push_back(large, arr[i]);
            }
        }
        sortNoOut(small);
        sortNoOut(large);

        Handler.clear(arr);

        System.arraycopy(small, 0, arr, 0, small.length);
        arr[small.length] = pivot;
        System.arraycopy(large, 0, arr,small.length+1, large.length);

        return arr;
    }
}
