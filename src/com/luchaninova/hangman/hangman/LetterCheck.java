package com.luchaninova.hangman.hangman;

import java.util.Set;

public class LetterCheck {
    boolean isCyrillic(String string) {
        boolean result = false;
        for (char a : string.toCharArray()) {
            if (Character.UnicodeBlock.of(a) == Character.UnicodeBlock.CYRILLIC) {
                result = true;
                break;
            }
        }
        return result;
    }

    boolean isValidLetter(String string) {
        return string.length() == 1 && isCyrillic(string) && Character.isLowerCase(string.charAt(0));
    }

    boolean isRepeatedLetter(Set<Character> letters2, Character letter2) {
        return letters2.contains(letter2);
    }
}
