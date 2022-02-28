package tt.theodor;

public class Main {

    private static void insertion(List<Integer> list) {
        print(list);
        List<Integer> result = new List<>();

        list.toFirst();
        result.append(list.getContent());
        list.remove();

        list.toFirst();
        result.toFirst();
        while (!list.isEmpty()) {
            Integer first = list.getContent();

            result.toFirst();
            while (first > result.getContent()) {
                result.next();

                if (!result.hasAccess()) {
                    result.append(first);
                    break;
                }
            }
            result.insert(first);

            list.remove();
        }

        print(result);
    }

    private static void selection(int[] arr) {
        print(arr);

        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j = i ; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }

            int cache = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = cache;
        }

        print(arr);
    }

    private static void quick(List<Integer> list) {
        print(list);

        list = quickSortLevel(list);

        print(list);
    }

    private static List<Integer> quickSortLevel(List<Integer> list) {
        if (list.isEmpty()) {
            return new List<Integer>();
        }
        list.toFirst();
        int pivot = list.getContent();
        list.next();

        List<Integer> smaller = new List<>();
        List<Integer> equal = new List<>();
        List<Integer> larger = new List<>();
        while (list.hasAccess()) {
            if (list.getContent() < pivot) {
                smaller.append(list.getContent());
            } else if (list.getContent() == pivot) {
                equal.append(list.getContent());
            } else if (list.getContent() > pivot) {
                larger.append(list.getContent());
            }
            list.next();
        }

        List<Integer> newList = quickSortLevel(smaller);
        newList.append(pivot);
        newList.concat(equal);
        newList.concat( quickSortLevel(larger) );

        return newList;
    }

    public static void main(String[] args) {
        System.out.println("Insertion sort:");
        insertion( arrToList(new int[]{3, 5, 1, 7, 5, 6, 3, 8}) );

        System.out.println();
        System.out.println("Selection sort:");
        selection(new int[]{3, 6, 2, 3, 4, 5, 1, 8, 9});

        System.out.println();
        System.out.println("Quick sort:");
        quick( arrToList(new int[]{6, 2, 8, 1, 2, 4, 12, 9, 14, 11}) );
    }

    private static List<Integer> arrToList(int[] arr) {
        List<Integer> result = new List<>();

        for (int i : arr) {
            result.append(i);
        }

        return result;
    }

    private static void print(List<Integer> l) {
        l.toFirst();
        while (l.hasAccess()) {
            System.out.print(l.getContent() + "; ");
            l.next();
        }
        System.out.println();
    }

    private static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + "; ");
        }
        System.out.println();
    }
}
