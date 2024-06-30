package com.luchaninova.hangman.reader;

import java.util.Scanner;

public class ConsoleReader implements Reader {

    Scanner scanner = new Scanner(System.in);

    @Override
    public String read() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}
