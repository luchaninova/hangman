package com.luchaninova.hangman;


import com.luchaninova.hangman.hangman.Game;
import com.luchaninova.hangman.printer.ConsolePrinter;
import com.luchaninova.hangman.reader.ConsoleReader;

import java.io.*;



public class Main {


    public static void main(String[] args) throws IOException {
        ConsolePrinter consolePrinter = new ConsolePrinter();
        consolePrinter.print("Начать новую игру - нажми 1. Выход - нажми любую клавишу");
        ConsoleReader consoleReader = new ConsoleReader();
        String message = consoleReader.read();
        Game game = new Game();
        game.start(message);
    }

}




