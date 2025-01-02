package com.luchaninova.hangman.hangman;
import com.luchaninova.hangman.fileReader.FileReader;
import com.luchaninova.hangman.exeption.FileNotFoundException;
import com.luchaninova.hangman.letter.IndexLetter;
import com.luchaninova.hangman.reader.IReader;
import com.luchaninova.hangman.reader.KeyboardReader;
import com.luchaninova.hangman.render.HangmanRenderer;
import com.luchaninova.hangman.render.LettersRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public interface IGame {
    final IReader reader = new KeyboardReader();
    final HangmanRenderer hangmanRenderer = new HangmanRenderer();
    final LetterChecker letterChecker = new LetterChecker();
    final LettersRenderer lettersRenderer = new LettersRenderer();
    static final int MAX_MISTAKES = 7;
    static final int MIN_MISTAKES = 1;


    default void startGame() {
        System.out.println("""
                  Приветствую! Это игра ВИСИЛИЦА
                 ***********************************
                 Начать НОВУЮ игру - нажми <<< 1 >>>
                    ВЫХОД - нажать любую клавишу
                 ***********************************""");
        launchGame(reader.read());
    }

    default void launchGame(String message) {
        if (message.equals("1")) startGameRound();
        else System.out.println("Игра закончена. До свидания!");
    }

    default void startGameRound() {
        System.out.println("""
                 ***********************************
                            Начнем игру!
                 ***********************************
                    Я выбрал слово, угадате его.
                Подходят только БУКВЫ РУССКОГО АЛФАВИТА
                                
                 Слово выводиться в зашифрованном виде.
                Если буква угадана, она появиться в слове
                          вместо звездочки.
                 ***********************************
                           Всего 6 ПОПЫТОК
                 ***********************************
                """);

        hangmanRenderer.renderHangmanText("Здесь отобразятся ошибки");
        hangmanRenderer.renderHangmanPicture(0);

        String word = "";
        try {
            word = getWord();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int wordLength = word.length();
        char[] wordCharArray = word.toCharArray();
        List<IndexLetter> letterIndices = transferLettersToList(wordCharArray);
        char[] guessArr = new char[wordCharArray.length];
        Arrays.fill(guessArr, '*');
        System.out.println("Это загаданное слово, все буквы закрыты звездочками");
        System.out.println(guessArr);

        startGameLoop(letterIndices, wordLength, guessArr, word);
        System.out.println("""
                 ***********************************
                          Игра закончена
                 ***********************************
                            Сыграем еще?
                  Набери 1 для НОВОГО РАУНДА ИГРЫ
                    или любую клавишу для ВЫХОДА
                 ***********************************
                """);
        launchGame(reader.read());
    }

    void startGameLoop(List<IndexLetter> letterIndices, int wordLength, char[] guessArr, String word);

    default int mistakeIncrementation(String word, Character character, int mistakesCount) {
        if (!word.contains(character.toString())) {
            hangmanRenderer.renderHangmanText("Ошибка. Это не правильная буква");
            hangmanRenderer.renderHangmanPicture(mistakesCount++);
        }
        return mistakesCount;
    }

    default void addLetterToWrongLetters(String word, Character character, Set<Character> wrongCharacters) {
        if (!word.contains(character.toString())) {
            wrongCharacters.add(character);
        }
    }

    default void addLetterToRightLetters(String word, Character character, Set<Character> rightCharacters) {
        if (word.contains(character.toString())) {
            rightCharacters.add(character);
        }
    }

    default boolean isLeftLetterToOpen(char[] guessArr) {
        return !Arrays.toString(guessArr).contains("*");
    }

    default boolean checkGameState(boolean isLeftLetterToOpen, int mistakesCount, String word) {
        if (isLeftLetterToOpen || mistakesCount == MAX_MISTAKES) {
            if (isLeftLetterToOpen) {
                checkGameStatus(true, word);
            }
            if (mistakesCount == MAX_MISTAKES) {
                checkGameStatus(false, word);
            }
            return false;
        }
        return true;
    }

    default void checkGameStatus(boolean isWin, String word) {
        System.out.println(isWin ? "Поздравляем! Это победа" : "Увы, проиграш. Было загадано слово <<<" + word + ">>>");
    }

    default String getWord() throws FileNotFoundException {
        FileReader fileReader = new FileReader();
        try {
            return fileReader.readFromFile();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Файл не найден. Ошибка " + e + " . Программа прекращает свою работу");
        }
    }

    default List<IndexLetter> transferLettersToList(char[] charArray) {
        List<IndexLetter> list = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            list.add(new IndexLetter(i, charArray[i]));
        }
        return list;
    }

    default void putLetterInGuessArr(List<IndexLetter> indexLetterList, int lettersToOpen, char[] guessArr, char letter) {
        for (IndexLetter indexLetter : indexLetterList) {
            if (letter == indexLetter.letter()) {
                lettersToOpen--;
                guessArr[indexLetter.index()] = letter;
            }
        }
    }
}
