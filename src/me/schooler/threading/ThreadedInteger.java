package me.schooler.threading;

public class ThreadedInteger {
    private int value;

    public ThreadedInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public synchronized int getSafeIncr() {
        return value++;
    }
}
