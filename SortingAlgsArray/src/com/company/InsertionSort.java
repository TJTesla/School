package com.company;

public class InsertionSort {
    public static void sort() {
        int[] arr = Handler.copy(Handler.arr1);

        for (int i = 0; i < arr.length; i++) {
            int curVal = arr[i];
            for (int j = 0; j < i; j++) {
                if (curVal < arr[j]) {
                    Handler.switchVals(arr, i, j);
                }
            }
            Handler.print(arr, i+1);
        }

        Handler.print(arr);
    }
}
