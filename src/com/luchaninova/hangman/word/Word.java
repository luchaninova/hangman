package com.luchaninova.hangman.word;

public class Word {

    /*public void setWord() {
        try {

        } catch ()
    }*/
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                '}';
    }
}
