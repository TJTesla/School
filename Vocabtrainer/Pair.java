package com.company;

public class Pair<FirstType, SecondType>
{
    FirstType first;
    SecondType second;

    public Pair() {
        first = null;
        second = null;
    }
    public Pair(FirstType pFirst, SecondType pSecond) {
        this.first = pFirst;
        this.second = pSecond;
    }

    public void setFirst(FirstType pFirst) {
        this.first = pFirst;
    }
    public void setSecond(SecondType pSecond) {
        this.second = pSecond;
    }

    public FirstType first() {
        return this.first;
    }
    public SecondType second() {
        return this.second;
    }

    public boolean isEmpty() {
        return this.first == null;
    }
}
