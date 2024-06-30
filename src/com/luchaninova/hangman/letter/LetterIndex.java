package com.luchaninova.hangman.letter;

public record LetterIndex(int index, char letter) {

    @Override
    public String toString() {
        return index + " " + letter;
    }
}
