package me.schooler.threading;

import static me.schooler.threading.Helper.*;

public class Kiste {
    private long cooldown;
    private int kistenNummer;

    protected static volatile ThreadedInteger kistenCount = new ThreadedInteger(1);

    public Kiste(long cooldown) {
        this.cooldown = cooldown;
        this.kistenNummer = kistenCount.getSafeIncr();
        log("Kiste Nummer #%d erstellt.".formatted(kistenNummer));
    }

    public long getCooldown() {
        return cooldown;
    }

    public int getKistenNummer() {
        return kistenNummer;
    }

    @Override
    public String toString() {
        return "Kiste{" +
                "cooldown=" + cooldown +
                ", kistenNummer=" + kistenNummer +
                '}';
    }
}
