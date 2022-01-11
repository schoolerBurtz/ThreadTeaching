package me.schooler.threading;

import java.util.Random;

import static me.schooler.threading.Helper.log;

public class Destroyer extends Worker{
    private int number;
    private boolean running;
    private Random random;
    private Runner runner;
    private boolean occupied;

    protected static volatile ThreadedInteger count = new ThreadedInteger(1);

    public Destroyer(Runner runner) {
        occupied = false;
        this.number = count.getSafeIncr();
        running = true;
        random = new Random();
        this.runner = runner;
    }

    public int getNumber() {
        return number;
    }


    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (running) {
            finish();
        }
    }

    public void finish() {
        Kiste kiste;

        do {
            kiste = runner.getKisteForWork();
            if (kiste == null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (kiste == null);

        log("Destroyer #%d hat Arbeit an Kiste #%d angefangen.".formatted(number, kiste.getKistenNummer()));
        occupied = true;
        try {
            Thread.sleep(kiste.getCooldown());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("Destroyer #%d hat Arbeit an Kiste #%d beendet.".formatted(number, kiste.getKistenNummer()));
        occupied = false;
    }

    @Override
    public boolean isOccupied() {
        return occupied;
    }

    @Override
    public String toString() {
        return "Destroyer{" +
                "number=" + number +
                ", occupied=" + occupied +
                '}';
    }
}