package com.company;

public class BubbleSort {
    public static void sort() {
        int[] arr = Handler.copy(Handler.arr1);
        for (int i = 0; i < arr.length; i++) {
            boolean changed = false;
            for (int j = 0; j < arr.length-1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    Handler.switchVals(arr, j, j+1);
                    changed = true;
                }
            }
            Handler.print(arr, i+1);
            if (!changed) {
                break;
            }
        }
        Handler.print(arr);
    }
}
