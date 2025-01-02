package com.luchaninova.hangman.hangman;

import com.luchaninova.hangman.letter.IndexLetter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GameWithHint implements IGame {

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
                     ***********************************""");
            System.out.println("Нужна ПОДСКАЗКА? Наберите цифру 0 или выбирайте букву");

            String letter = reader.read();
            Character character;

            if (letter.equals("0")) {
                character = getHintLetter(rightCharacters, word);
            } else {
                character = letter.charAt(0);
            }

            if (letterChecker.isInvalidLetter(letter, character)) continue;
            if (letterChecker.isLetterRepeats(rightCharacters, character, wrongCharacters)) continue;

            addLetterToRightLetters(word, character, rightCharacters);
            addLetterToWrongLetters(word, character, wrongCharacters);
            mistakesCount = mistakeIncrementation(word, character, mistakesCount);

            putLetterInGuessArr(letterIndices, wordLength, guessArr, character);
            System.out.println(guessArr);

            System.out.println("Список использованных букв >>>");
            System.out.println("Правильные буквы:");
            lettersRenderer.renderLetters(rightCharacters);
            System.out.println("\nОшибочные буквы:");
            lettersRenderer.renderLetters(wrongCharacters);
            System.out.println();

        } while (checkGameState(isLeftLetterToOpen(guessArr), mistakesCount, word));
    }

    private Character getHintLetter(Set<Character> rightCharacters, String word) {
        HashSet<Character> collect = word.chars().mapToObj(ch -> (char) ch).collect(Collectors.toCollection(HashSet::new));
        collect.removeAll(rightCharacters);
        return collect.iterator().next();
    }

}
