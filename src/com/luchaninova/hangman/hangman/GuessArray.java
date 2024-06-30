package com.luchaninova.hangman.hangman;

import com.luchaninova.hangman.letter.LetterIndex;

import java.util.List;

public class GuessArray {
    static int getLettersToOpen(List<LetterIndex> letterIndices, int lettersToOpen, char[] guessArr, char validLetter) {
        for (LetterIndex letterIndex : letterIndices) {
            if (validLetter == letterIndex.letter()) {
                lettersToOpen--;
                guessArr[letterIndex.index()] = validLetter;
            }
        }
        return lettersToOpen;
    }

    static void printGuessArr(List<LetterIndex> list, char c, char[] guessArr) {
        for (LetterIndex letterIndex : list) {
            if (c == letterIndex.letter()) {
                guessArr[letterIndex.index()] = c;
            }
        }
        System.out.println(guessArr);
    }
}
