package com.company;

public class BinarySearch {
    public static void search() {
        int[] arr = Handler.arr1;
        QuickSort.sort(arr);
        int elem = 7;
        boolean found = search(arr, elem);
        if (found) {
            System.out.println("Das Element " + elem + " wurde im Array gefunden");
        } else {
            System.out.println("Das Element " + elem + " wurde nicht im Array gefunden");
        }
    }

    private static boolean search(int[] arr, int elem) {
        if (arr.length == 1) {
            return arr[0] == elem;
        }
        int[] small = new int[arr.length/2];
        int[] large = new int[arr.length/2 + arr.length%2];
        System.arraycopy(arr, 0, small, 0, small.length);
        System.arraycopy(arr, arr.length/2, large, 0, large.length);
        boolean found;
        if (elem <= small[small.length-1]) {
            found = search(small, elem);
        } else {
            found = search(large, elem);
        }
        return found;
    }
}
