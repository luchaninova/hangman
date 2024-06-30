package com.luchaninova.hangman.hangman;

import com.luchaninova.hangman.printer.ConsolePrinter;

public class HangmanPicture {

    ConsolePrinter consolePrinter = new ConsolePrinter();

    public void printHangman(int errorCount) {
        switch (errorCount) {
            case 0 -> consolePrinter.print(
                    """
                            Это висилица
                               _________
                               |/      |
                               |
                               |
                               |
                               |
                            ___|___"""
            );
            case 1 -> consolePrinter.print(
                    """
                            1 ошибка
                               _________
                               |/      |
                               |       *
                               |
                               |
                               |
                            ___|___"""
            );
            case 2 -> consolePrinter.print(
                    """
                               2 ошибка
                               _________
                               |/      |
                               |       *
                               |      /
                               |
                               |
                            ___|___"""
            );
            case 3 -> consolePrinter.print(
                    """
                               3 ошибка
                               _________
                               |/      |
                               |       *
                               |      /|
                               |
                               |
                            ___|___"""
            );
            case 4 -> consolePrinter.print(
                    """
                               4 ошибка
                               _________
                               |/      |
                               |       *
                               |      /|\\
                               |
                               |
                            ___|___"""
            );
            case 5 -> consolePrinter.print(
                    """
                            5 ошибка
                            _________
                            |/      |
                            |       *
                            |      /|\\
                            |      /
                            |
                            ___|___"""
            );

            case 6 -> consolePrinter.print(
                    """
                               Последняя ошибка
                               _________
                               |/      |
                               |       *
                               |      /|\\
                               |      / \\
                               |
                            ___|___"""
            );
            default -> throw new IllegalStateException("Unexpected value: " + errorCount);
        }

    }
}
