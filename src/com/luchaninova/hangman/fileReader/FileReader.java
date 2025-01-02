package com.luchaninova.hangman.fileReader;

import com.luchaninova.hangman.exeption.FileNotFoundException;

import java.io.*;
import java.util.Random;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

public class FileReader {

    private final Random rand = new Random();

    public String readFromFile() throws FileNotFoundException {
        String word = "";
        String fileName = "file/words.txt";
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "r")) {
            int fileLength = Integer.parseInt(randomAccessFile.readLine());
            int randomPosition = getRandomPosition(fileLength);
            randomAccessFile.seek(randomPosition);
            randomAccessFile.readLine();
            long filePointer = randomAccessFile.getFilePointer();
            randomAccessFile.seek(filePointer);
            word = randomAccessFile.readLine();
            return new String(word.getBytes(ISO_8859_1), UTF_8);
        } catch (IOException e) {
            throw new FileNotFoundException("К сожалению файл не может быть прочитан. Ошибка " + e);
        }
    }

    @Override
    public void close() { // default implementation ignored

    }

    public int getRandomPosition(int max) {
        return rand.nextInt(1, max);
    }
}
