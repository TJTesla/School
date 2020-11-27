package com.company;

import java.util.Arrays;

public class Handler {
    public static int[] arr1 = { 3, 5, 1, 7, 3, 5, 9, 3, 5, 6 };
    public static int[] arr2 = { 4, 7, 2, 4, 8, 3, 5, 4, 5, 6, 8 };
    public static int[] arr3 = { 8, 1, 5, 2, 5, 7 };

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length-1) {
                System.out.print(", ");
            } else {
                System.out.println("");
            }
        }
    }

    public static void print(int[] arr, int iteration) {
        System.out.println(iteration + ". Durchgang: ");
        Handler.print(arr);
        System.out.println("");
    }

    public static void clear(int[] arr) {
        Arrays.fill(arr, 0);
    }

    public static int[] copy(int[] arr) {
        int[] retArr = new int[arr.length];
        System.arraycopy(arr, 0, retArr, 0, arr.length);
        return retArr;
    }

    public static void switchVals(int[] arr, int index1, int index2) {
        int save = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = save;
    }

    public static int[] push_back(int[] arr, int val) {
        int[] newArr = new int[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        newArr[newArr.length - 1] = val;
        return newArr;
    }
}
