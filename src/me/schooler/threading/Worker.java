package me.schooler.threading;

import java.util.Random;

public abstract class Worker extends Thread{
    private int number;
    private boolean running;
    private Random random;
    private Runner runner;
    private boolean occupied;

    protected static volatile ThreadedInteger count;

    public abstract int getNumber();

    public abstract boolean isRunning();

    public abstract void run();

    public abstract boolean isOccupied();
}
