package me.schooler.threading;

import java.util.Random;

import static me.schooler.threading.Helper.log;

public class Creator extends Thread {
    private int number;
    private boolean running;
    private Random random;
    private Runner runner;

    protected static volatile ThreadedInteger count = new ThreadedInteger(1);

    public Creator(Runner runner) {
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
            Kiste newKiste = new Kiste(random.nextLong(420*2, 1337*11));
            workOn(newKiste);
        }
    }

    public void workOn(Kiste kiste) {
        log("Creator #%d hat Arbeit an Kiste #%d angefangen.".formatted(number, kiste.getKistenNummer()));
        do {
            try {
                Thread.sleep(kiste.getCooldown());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!runner.submitKiste(kiste));
        log("Creator #%d hat Arbeit an Kiste #%d beendet.".formatted(number, kiste.getKistenNummer()));
    }
}
