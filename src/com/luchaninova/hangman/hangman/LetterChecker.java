package com.luchaninova.hangman.hangman;

import java.util.Set;

import static com.luchaninova.hangman.hangman.IGame.letterChecker;

public class LetterChecker {

    private boolean isRepeatedLetter(Set<Character> letters, Character letter) {
        return letters.contains(letter);
    }

    boolean isLetterRepeats(Set<Character> rightCharacters, Character character, Set<Character> wrongCharacters) {
        if (letterChecker.isRepeatedLetter(rightCharacters, character)
                || letterChecker.isRepeatedLetter(wrongCharacters, character)) {
            System.out.println("Эта буква уже была");
            return true;
        }
        return false;
    }

    boolean isInvalidLetter(String letter, Character character) {
        if (letter.length() > 1
                || Character.isUpperCase(letter.charAt(0))
                || (Character.UnicodeBlock.of(character) != Character.UnicodeBlock.CYRILLIC)) {
            System.out.println("Некорректное значение, введите 1 букву");
            return true;
        }
        return false;
    }
}
