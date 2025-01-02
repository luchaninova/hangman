package com.luchaninova.hangman.hangman;

import com.luchaninova.hangman.letter.IndexLetter;
import com.luchaninova.hangman.render.LettersRenderer;


import java.util.*;


public class Game implements IGame {


    @Override
    public void startGameLoop(List<IndexLetter> letterIndices, int wordLength, char[] guessArr, String word) {
        Set<Character> rightCharacters = new HashSet<>();
        Set<Character> wrongCharacters = new HashSet<>();
        int mistakesCount = MIN_MISTAKES;

        do {
            System.out.println("""
                     ***********************************
                              Нужно набрать 1
                           букву русского алфавита
                     ***********************************
                        Список использованных букв >>>""");

            LettersRenderer lettersRenderer = new LettersRenderer();
            System.out.println("Правильные буквы:");
            lettersRenderer.renderLetters(rightCharacters);
            System.out.println("\nОшибочные буквы:");
            lettersRenderer.renderLetters(wrongCharacters);
            System.out.println("\n ***********************************");

            String letter = reader.read();
            Character character = letter.charAt(0);

            if (letterChecker.isInvalidLetter(letter, character)) continue;
            if (letterChecker.isLetterRepeats(rightCharacters, character, wrongCharacters)) continue;

            addLetterToRightLetters(word, character, rightCharacters);
            addLetterToWrongLetters(word, character, wrongCharacters);
            mistakesCount = mistakeIncrementation(word, character, mistakesCount);

            putLetterInGuessArr(letterIndices, wordLength, guessArr, character);
            System.out.println(guessArr);

        } while (checkGameState(isLeftLetterToOpen(guessArr), mistakesCount, word));
    }

}
