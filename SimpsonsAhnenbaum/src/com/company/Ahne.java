package com.company;

public class Ahne {
    private String vorname;
    private String nachname;
    private Gender geschlecht;

    public Ahne(String pVorname, String pNachname, Gender pGeschlecht) {
        this.vorname  = pVorname;
        this.nachname = pNachname;
        this.geschlecht = pGeschlecht;
    }

    public String getVorname() {
        return this.vorname;
    }

    public String getNachname() {
        return this.nachname;
    }

    public String getName() {
        return (this.nachname + ", " + this.vorname);
    }

    public Gender getGeschlecht() {
        return this.geschlecht;
    }
}
