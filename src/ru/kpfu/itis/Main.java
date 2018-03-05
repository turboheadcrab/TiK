package ru.kpfu.itis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        // write your code here

        String text = null;
        try {
            text = new String(Files.readAllBytes(Paths.get("T:\\Pogromist\\TiK\\azaz.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        SingleCharOps first = new SingleCharOps(text);
        DoubleCharOps second = new DoubleCharOps(text);

        System.out.println(first);
        System.out.println();
        System.out.println(second);
    }
}
