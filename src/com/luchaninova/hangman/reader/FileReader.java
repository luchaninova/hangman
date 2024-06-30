package com.luchaninova.hangman.reader;

import java.io.*;
import java.util.Random;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

public class FileReader implements Reader{

    Random rand = new Random();

    @Override
    public String read() {
        String fileName = "file/words.txt";
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "r")){
            int fileLength = Integer.parseInt(randomAccessFile.readLine());
            int randomPosition = getRandomPosition(fileLength);
            randomAccessFile.seek(randomPosition);
            randomAccessFile.readLine();
            long filePointer = randomAccessFile.getFilePointer();
            randomAccessFile.seek(filePointer);
            String word = randomAccessFile.readLine();
            return new String(word.getBytes(ISO_8859_1), UTF_8);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getRandomPosition(int max) {
        return rand.nextInt(1,max);
    }
}
