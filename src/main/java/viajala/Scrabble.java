package scrabble;

import java.util.Scanner;
import java.io.IOException;
import java.lang.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.lang.ArrayUtils;

public class Scrabble {

    private final long id;
    private String word;

    public Scrabble(long id, String letters) {
        this.id = id;
        try {
            this.word = this.searchWordInFile("prueba.txt", letters);
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
    }

    public long getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    private String searchWordInFile(String fileName, String letters) throws IOException {
        // Get input letters as array
        String lettersClean = letters.replace(",","");
        char[] letterList = lettersClean.trim().toCharArray();

        String word = "";
        char[] wordLetterList;

        //Open file in scanner
        Path path = Paths.get(fileName);
        try (Scanner scanner =  new Scanner(path)){

            // For each word in file, check if word can be formed with given letters and return first match
            while (scanner.hasNextLine()){
                word = scanner.nextLine().trim();
                wordLetterList = word.trim().toCharArray();

                for(int i = 0; i < letterList.length; i++) {
                    for(int j = 0; j < wordLetterList.length; j++) {
                        if(letterList[i] == wordLetterList[j]) {
                            letterList = (char[])ArrayUtils.remove(letterList, i);
                            wordLetterList = (char[]) ArrayUtils.remove(wordLetterList, j);
                        }
                    }
                }
                if(wordLetterList.length == 0) {
                    return word;
                } else {
                    letterList = lettersClean.trim().toCharArray();
                }
            }
        }
        return "";
    }

}