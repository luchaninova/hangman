package com.luchaninova.hangman.hangman;

import com.luchaninova.hangman.printer.ConsolePrinter;

public class PrintHangman {
    ConsolePrinter consolePrinter = new ConsolePrinter();
    HangmanPicture hangmanPicture = new HangmanPicture();

    void printHangmanPicture(String message, int mistakesCount) {
        consolePrinter.print(message);
        hangmanPicture.printHangman(mistakesCount);
    }


}
