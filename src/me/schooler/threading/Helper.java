package me.schooler.threading;

import java.util.Arrays;

public class Helper {
    public static void log(Object o) {
        System.out.println(o.toString());
    }

    public synchronized static void log(Object... os) {
        Arrays.stream(os).forEach((e) -> {System.out.print(e.toString() + ", ");});
        System.out.println();
    }

    public static Thread getLogger(Runner runner) {
        return new Thread() {
            private Runner run = runner;
            @Override
            public synchronized void run() {
                while (true) {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    String out = "[";
                    for (Kiste k : run.getKistenQueue()) {
                        out += (k != null) ? k.toString() + ", " : "null, ";
                    }
                    out += "]\n";
                    for (Creator c : run.getCreatorQueue()) {
                        out += (c != null) ? c.toString() + ", ": "null, ";
                    }out += "]\n";
                    for (Destroyer c : run.getDestroyerQueue()) {
                        out += (c != null) ? c.toString() + ", ": "null, ";
                    }out += "]\n";
                    System.out.print(out);
                }
            }
        };
    }

    public static void main(String[] args) {
        new Runner(5, false);
    }
}
