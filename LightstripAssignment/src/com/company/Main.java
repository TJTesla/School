package com.company;

public class Main {
    public static class LED {
        private boolean leuchtet;

        public LED() {
            this.leuchtet = false;
        }

        public void lichtAn() {
            this.leuchtet = true;
        }

        public void lichtAus() {
            this.leuchtet = false;
        }
    }

    public static class Steuerung {
        private int anzahlLEDs;
        private List<LED> lichterkette;

        public Steuerung() {
            this.anzahlLEDs = 0;
            lichterkette = new List<LED>();
        }

        public void leuchten01() {
            lichterkette.toFirst();
            while (lichterkette.hasAccess()) {
                lichterkette.getContent().lichtAn();
                lichterkette.next();
            }
        }

        public void leuchten02() {
            int counter = 0;
            lichterkette.toFirst();
            while (lichterkette.hasAccess()) {
                if (counter % 2 == 0) {
                    lichterkette.getContent().lichtAn();
                }
                lichterkette.next();
                counter++;
            }
        }

        public void leuchten03() {
            int counter = 0;
            lichterkette.toFirst();
            while (lichterkette.hasAccess()) {
                if (counter % 3 == 0) {
                    lichterkette.getContent().lichtAn();
                }
                lichterkette.next();
                counter++;
            }
        }

        public void lichterketteAnhaengen(List<LED> pLichterkette) {
            pLichterkette.toFirst();
            while (pLichterkette.hasAccess()) {
                anzahlLEDs++;
                pLichterkette.next();
            }
            lichterkette.concat(pLichterkette);
        }
    }

    public static void insertionSort(List<Integer> list) {
        List<Integer> speicher = new List<Integer>();
        list.toFirst();
        speicher.append(list.getContent());
        list.remove();
        while (!list.isEmpty()) {
            list.toFirst();
            int curObject = list.getContent();

            speicher.toFirst();
            while (speicher.hasAccess()) {
                if (speicher.getContent() > curObject) {
                    break;
                }
                speicher.next();
            }

            if (speicher.hasAccess()) {
                speicher.insert(curObject);
            } else {
                speicher.append(curObject);
            }

            list.remove();
        }
        list.concat(speicher);
    }

    public static void selectionSort(List<Integer> list) {
        List<Integer> speicher = new List<Integer>();
        while (!list.isEmpty()) {
            // Find minimum
            list.toFirst();
            int min = list.getContent();
            int counter = 0;
            int position = 0;
            while (list.hasAccess()) {
                if (list.getContent() < min) {
                    min = list.getContent();
                    position = counter;
                }
                list.next();
                counter++;
            }

            // Delete the lowest element
            list.toFirst();
            for (int i = 0; i < position; i++) {
                list.next();
            }
            list.remove();

            speicher.append(min);
        }
        list.concat(speicher);
    }

    public static int size(List<Integer> list) {
        int counter = 0;
        list.toFirst();
        while (list.hasAccess()) {
            counter++;
            list.next();
        }
        return counter;
    }

    public static void quicksort(List<Integer> list) {
        if (size(list) <= 1) {
            return;
        }
        list.toFirst();
        int pivot = list.getContent();
        list.remove();

        List<Integer> klein = new List<Integer>();
        List<Integer> gross = new List<Integer>();
        while (!list.isEmpty()) {
            list.toFirst();
            if (list.getContent() <= pivot) {
                klein.append(list.getContent());
            } else {
                gross.append(list.getContent());
            }
            list.remove();
        }

        quicksort(klein);
        quicksort(gross);

        list.concat(klein);
        list.append(pivot);
        list.concat(gross);
    }

    public static void main(String[] args) {
        List<Integer> list = new List<Integer>();
        list.append(4);
        list.append(7);
        list.append(1);
        list.append(2);
        list.append(9);
        list.append(3);

        quicksort(list);

        list.toFirst();
        while(list.hasAccess()) {
            System.out.println(list.getContent());
            list.next();
        }
    }
}

