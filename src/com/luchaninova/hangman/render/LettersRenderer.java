package com.luchaninova.hangman.render;

import java.util.Set;

public class LettersRenderer {


    public void renderLetters(Set<Character> characters) {
        for (Character character : characters) {
            System.out.print(character + " ");
        }
    }

}
