package com.luchaninova.hangman.constant;

public enum HangmanPicture {
    ZERO("""
                _________
               |/      |
               |
               |
               |
               |
            ___|___"""),
    ONE("""
               _________
               |/      |
               |       *
               |
               |
               |
            ___|___"""),
    TWO("""
               _________
               |/      |
               |       *
               |      /
               |
               |
            ___|___"""),
    THREE("""
               _________
               |/      |
               |       *
               |      /|
               |
               |
            ___|___"""),
    FOUR("""
               _________
               |/      |
               |       *
               |      /|\\
               |
               |
            ___|___"""),
    FIVE("""
               _________
               |/      |
               |       *
               |      /|\\
               |      /
               |
            ___|___"""),
    SIX("""
               _________
               |/      |
               |       *
               |      /|\\
               |      / \\
               |
            ___|___""");

    private final String picture;

    HangmanPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }
}
