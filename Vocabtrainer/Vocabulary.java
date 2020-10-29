package com.company;

import java.util.Random;

public class Vocabulary {
    List<String> firstLang;
    List<String> secondLang;
    int size;

    public Vocabulary() {
        this.firstLang = new List<>();
        this.secondLang = new List<>();
        size = 0;
    }

    public Vocabulary(String pFirst, String pSecond) {
        this.firstLang = new List<>();
        this.secondLang = new List<>();
        size = 0;

        this.firstLang.append(pFirst);
        this.secondLang.append(pSecond);
        size++;
    }

    public void add(String pFirst, String pSecond) {
        this.firstLang.append(pFirst);
        this.secondLang.append(pSecond);
        size++;
    }

    public boolean isIn(String pWord, Language lang) {
        List<String> tempList;
        if (lang.equals(Language.FIRST)) {
            tempList = this.firstLang;
        } else {
            tempList = this.secondLang;
        }

        tempList.toFirst();
        while (tempList.hasAccess()) {
            if (tempList.getContent().equals(pWord)) {
                return true;
            }
            tempList.next();
        }
        return false;
    }

    public String getRandomWord(Language lang) {
        Random rng = new Random();
        List<String> tempList;
        if (lang.equals(Language.FIRST)) {
            tempList = this.firstLang;
        } else {
            tempList = this.secondLang;
        }

        int rndIndex = rng.nextInt(this.size);
        tempList.toFirst();
        for (int i = 0; i < rndIndex; i++) {
            tempList.next();
        }
        return tempList.getContent();
    }

    public String getFirstWordInFirstLanguage() {
        this.firstLang.toFirst();
        return this.firstLang.getContent();
    }

}
