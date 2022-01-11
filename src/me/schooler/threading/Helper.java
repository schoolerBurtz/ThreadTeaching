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

    public static void main(String[] args) {
        new Runner(5);
    }
}
