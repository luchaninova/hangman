package com.luchaninova.hangman.hangman;

import com.luchaninova.hangman.letter.LetterIndex;

import com.luchaninova.hangman.reader.ConsoleReader;
import com.luchaninova.hangman.reader.FileReader;
import com.luchaninova.hangman.reader.Reader;


import java.io.IOException;
import java.util.*;


public class Game {

    Reader consoleReader = new ConsoleReader();
    PrintHangman printHangman = new PrintHangman();
    LetterCheck letterCheck = new LetterCheck();


    public void start(String message) throws IOException {
        if (message.equalsIgnoreCase("1")) {
            launchGame();
        } else {
            System.out.println("Выход из игры");
            consoleReader.close();
        }
    }

    private void launchGame() throws IOException {
        System.out.printf("Начнем игру! Я выбрал слово, угадай его. Выбери букву русского алфавита >>>" +
                "%nЕсли угадаешь, в слове откроеться буква.%nУ тебя 6 попыток%n");
        printHangman.printHangmanPicture("Здесь отобразятся ошибки", 0);

        String word = getWord();
        int lettersToOpen = word.length();
        char[] wordCharArray = word.toCharArray();
        List<LetterIndex> letterIndices = getLetterIndexList(wordCharArray);
        char[] guessArr = new char[wordCharArray.length];
        Arrays.fill(guessArr, '*');
        System.out.println("Это загаданное слово, все буквы закрыты звездочками");
        System.out.println(guessArr);

        int mistakesCount = 1;
        Set<Character> characters = new HashSet<>();

        doGame(letterIndices, lettersToOpen, guessArr, word, mistakesCount, characters);
        System.out.println("Игра закончена. Сыграем еще? Набери 1 для новой игры или любую клавишу для выхода");
        start(consoleReader.read());
    }

    private void doGame(List<LetterIndex> letterIndices, int lettersToOpen, char[] guessArr, String word, int mistakesCount,
                        Set<Character> characters) {
        while (!isEnd(lettersToOpen, mistakesCount, word)) {
            System.out.println("Нужно набрать 1ну букву русского алфавита");
            String letter = consoleReader.read();
            Character characterLetter = 0;
            if (letterCheck.isValidLetter(letter)) {
                characterLetter = letter.charAt(0);
                if (letterCheck.isRepeatedLetter(characters, characterLetter)) {
                    System.out.println("Эта буква уже была");
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

    private static boolean isEnd(int lettersToOpen, int mistakesCount, String word) {
        if (lettersToOpen == 0 || mistakesCount == 7) {
            if (lettersToOpen == 0) {
                isWin(true, word);
            }
            if (mistakesCount == 7) {
                isWin(false, word);
            }
            return true;
        }
        return false;
    }

    private static void isWin(boolean win, String word) {
        if (win) {
            System.out.println("Поздравляем! Это победа");
        } else {
            System.out.println("Увы, проиграш. Было загадано слово <<<" + word + ">>>");
        }
    }

    private static List<LetterIndex> getLetterIndexList(char[] charArray) {
        List<LetterIndex> list = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            list.add(new LetterIndex(i, charArray[i]));
        }
        return list;
    }

    private static String getWord() {
        FileReader fileReader = new FileReader();
        return fileReader.read();
    }
}
