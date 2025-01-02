package com.luchaninova.hangman.render;


import com.luchaninova.hangman.constant.HangmanPicture;

public class HangmanRenderer {

    private HangmanPicture[] getHangmanArr() {
        HangmanPicture[] hangmanArr = new HangmanPicture[7];
        hangmanArr[0] = HangmanPicture.ZERO;
        hangmanArr[1] = HangmanPicture.ONE;
        hangmanArr[2] = HangmanPicture.TWO;
        hangmanArr[3] = HangmanPicture.THREE;
        hangmanArr[4] = HangmanPicture.FOUR;
        hangmanArr[5] = HangmanPicture.FIVE;
        hangmanArr[6] = HangmanPicture.SIX;
        return hangmanArr;
    }

    public void renderHangmanText(String message) {
        AccompanyingHangmanText hangmanText = new AccompanyingHangmanText();
        hangmanText.renderingAccompanyingHangmanText(message);
    }

    public void renderHangmanPicture(int pictureNumber) {
        HangmanPicture[] hangmanArr = getHangmanArr();
        System.out.println(hangmanArr[pictureNumber].getPicture());
    }


}
