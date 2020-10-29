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

    public static void main(String[] args) {

    }
}

