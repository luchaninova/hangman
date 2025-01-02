package com.luchaninova.hangman.letter;

public record IndexLetter(int index, char letter) {

    @Override
    public String toString() {
        return index + " " + letter;
    }
}
