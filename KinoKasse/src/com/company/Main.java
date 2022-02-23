package com.company;

import java.util.Random;

public class Main {

    public static class Kunde {
        private int guthaben;

        Kunde(int guthaben) { this.guthaben = guthaben; }
        public int getGuthaben() { return guthaben; }
        public void setGuthaben(int guthaben) { this.guthaben = guthaben; }
    }

    public static class Kasse {
        static int filmPreis = 10, popcornPreis = 4, getraenkPreis = 6;

        static void wasKannIchKaufen(Kunde kunde) {
            if (kunde.getGuthaben() >= filmPreis+popcornPreis+getraenkPreis) {
                System.out.println("Sie können sich den Film anschauen, Popcorn essen und dabei ein Getraenk trinken!");
            } else if (kunde.getGuthaben() >= filmPreis+popcornPreis) {
                System.out.println("Sie können sich den Film anschauen und dabei Popcorn essen!");
            } else if (kunde.getGuthaben() >= filmPreis) {
                System.out.println("Sie können sich den Film anschauen!");
            } else {
                System.out.println("Sie haben leider nicht genügend Geld, um irgendetwas zu kaufen.");
            }
        }
    }

    public static void main(String[] args) {
        Kunde[] kundenListe = new Kunde[5];
        Random rand = new Random();
        for (int i = 0; i < kundenListe.length; i++) {
            kundenListe[i] = new Kunde(rand.nextInt() % 25);
        }
        for (Kunde k : kundenListe) {
            Kasse.wasKannIchKaufen(k);
        }

        /* Kasse.wasKannIchKaufen(new Kunde(9));
        Kasse.wasKannIchKaufen(new Kunde(12));
        Kasse.wasKannIchKaufen(new Kunde(16));
        Kasse.wasKannIchKaufen(new Kunde(24)); */

    }
}
