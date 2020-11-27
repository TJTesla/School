package com.company;

public class SelectionSort {
    public static void sort() {
        int[] arr = Handler.copy(Handler.arr3);

        for (int i = 0; i < arr.length; i++) {
            int highestIndex = findMax(arr, arr.length-i);
            Handler.switchVals(arr, highestIndex, arr.length-i);

            Handler.print(arr, i+1);
        }

        Handler.print(arr);
    }

    private static int findMax(int[] arr, int maxBorder) {
        int highestIndex = 0;
        int highestVal = arr[0];
        for (int i = 0; i < maxBorder; i++) {
            if (arr[i] > highestVal) {
                highestVal = arr[i];
                highestIndex = i;
            }
        }
        return highestIndex;
    }
}
