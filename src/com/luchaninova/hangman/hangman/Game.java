package com.luchaninova.hangman.hangman;

import com.luchaninova.hangman.letter.LetterIndex;
import com.luchaninova.hangman.printer.ConsolePrinter;
import com.luchaninova.hangman.reader.ConsoleReader;
import com.luchaninova.hangman.reader.FileReader;


import java.io.IOException;
import java.util.*;


public class Game {

    ConsolePrinter consolePrinter = new ConsolePrinter();
    ConsoleReader consoleReader = new ConsoleReader();
    PrintHangman printHangman = new PrintHangman();
    LetterCheck letterCheck = new LetterCheck();


    public void start(String message) throws IOException {
        if (message.equalsIgnoreCase("1")) {
            launchGame();
        } else {
            consolePrinter.print("Выход из игры");
            consoleReader.close();
        }
    }

    private void launchGame() throws IOException {
        consolePrinter.print(("Начнем игру! Я выбрал слово, угадай его. Выбери букву русского алфавита >>>" +
                "%nЕсли угадаешь, в слове откроеться буква.%nУ тебя 6 попыток").formatted());
        printHangman.printHangmanPicture("Здесь отобразятся ошибки", 0);

        String word = getWord();
        int lettersToOpen = word.length();
        char[] wordCharArray = word.toCharArray();
        List<LetterIndex> letterIndices = getLetterIndexList(wordCharArray);
        char[] guessArr = new char[wordCharArray.length];
        Arrays.fill(guessArr, '*');
        consolePrinter.print("Это загаданное слово, все буквы закрыты звездочками");
        System.out.println(guessArr);

        int mistakesCount = 1;
        Set<Character> characters = new HashSet<>();

        doGame(letterIndices, lettersToOpen, guessArr, word, mistakesCount, characters);
        consolePrinter.print("Игра закончена. Сыграем еще? Набери 1 для новой игры или любую клавишу для выхода");
        start(consoleReader.read());
    }

    private void doGame(List<LetterIndex> letterIndices, int lettersToOpen, char[] guessArr, String word, int mistakesCount,
                        Set<Character> characters) {
        while (!isEnd(lettersToOpen, mistakesCount, consolePrinter, word)) {
            consolePrinter.print("Нужно набрать 1ну букву русского алфавита");
            String letter = consoleReader.read();
            Character characterLetter = 0;
            if (letterCheck.isValidLetter(letter)) {
                characterLetter = letter.charAt(0);
                if (letterCheck.isRepeatedLetter(characters, characterLetter)) {
                    consolePrinter.print("Эта буква уже была");
                    continue;
                } else {
                    characters.add(characterLetter);
                }
                if (!word.contains(letter)) {
                    printHangman.printHangmanPicture("Ошибка. Это не правильная буква", mistakesCount++);
                }
            }
            char validLetter = characterLetter;
            lettersToOpen = GuessArray.getLettersToOpen(letterIndices, lettersToOpen, guessArr, validLetter);
            GuessArray.printGuessArr(letterIndices, validLetter, guessArr);
        }
    }

    private static boolean isEnd(int lettersToOpen, int mistakesCount, ConsolePrinter consolePrinter, String word) {
        if (lettersToOpen == 0 || mistakesCount == 7) {
            if (lettersToOpen == 0) {
                isWin(true, consolePrinter, word);
            }
            if (mistakesCount == 7) {
                isWin(false, consolePrinter, word);
            }
            return true;
        }
        return false;
    }

    private static void isWin(boolean win, ConsolePrinter consolePrinter, String word) {
        if (win) {
            consolePrinter.print("Поздравляем! Это победа");
        } else {
            consolePrinter.print("Увы, проиграш. Было загадано слово <<<" + word + ">>>");
        }
    }

    private static List<LetterIndex> getLetterIndexList(char[] charArray) {
        List<LetterIndex> list = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            list.add(new LetterIndex(i, charArray[i]));
        }
        return list;
    }

    private static String getWord() throws IOException {
        FileReader fileReader = new FileReader();
        return fileReader.readFromFile();
    }
}
