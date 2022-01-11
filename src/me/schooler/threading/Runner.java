package me.schooler.threading;

import java.util.Arrays;

import static me.schooler.threading.Helper.log;

public class Runner {
    private Kiste[] kistenQueue;
    private Creator[] creatorQueue;
    private Destroyer[] destroyerQueue;

    public Runner(int size, boolean logging) {
        kistenQueue = new Kiste[size];
        creatorQueue = new Creator[size];
        destroyerQueue = new Destroyer[size];

        for (int i = 0; i < size; i++) {
            creatorQueue[i] = new Creator(this);
            destroyerQueue[i] = new Destroyer(this);
        }

        if (logging) Helper.getLogger(this).start();
        for (int i = 0; i < size; i++) {
            creatorQueue[i].start();
            destroyerQueue[i].start();
        }
    }

    public synchronized boolean submitKiste(Kiste newKiste) {
        for (int i = 0; i < kistenQueue.length; i++) {
            if (kistenQueue[i] == null) {
                kistenQueue[i] = newKiste;
                return true;
            }
        }
        return false;
    }

    public synchronized Kiste getKisteForWork() {
        boolean ready = false;
        Kiste result = null;
        Kiste[] newKisten = new Kiste[kistenQueue.length];
        int k = 0;
        for (int i = 0; i < kistenQueue.length; i++) {
            if (!ready && kistenQueue[i] != null) {
                result = kistenQueue[i++];
                ready = true;
            }
            newKisten[k] = kistenQueue[i];
            k++;
        }
        kistenQueue = newKisten;
        return result;
    }

    public synchronized Kiste[] getKistenQueue() {
        return kistenQueue;
    }

    public synchronized Creator[] getCreatorQueue() {
        return creatorQueue;
    }

    public synchronized Destroyer[] getDestroyerQueue() {
        return destroyerQueue;
    }
}
