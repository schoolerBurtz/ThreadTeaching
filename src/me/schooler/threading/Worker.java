package me.schooler.threading;

import java.util.Random;

public abstract class Worker {
    private int number;
    private boolean alive;
    private Random random;
    private Runner runner;

    protected static volatile ThreadedInteger count;

    public abstract int getNumber();

    public abstract boolean isAlive();

    public abstract void run();
}
