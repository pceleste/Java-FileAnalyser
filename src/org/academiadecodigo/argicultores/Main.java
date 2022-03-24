package org.academiadecodigo.argicultores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        String fileName1 = "file1.txt";
        String fileName2 = "file2.txt";

        Main main = new Main();

        System.out.println("Number of Words: " + main.getNumberWords(fileName1));

        System.out.println("The first longest word is: " + main.findFirstWordLonger(fileName1, 7));

        System.out.println("The longest words are:");
        main.getLongestWords(fileName1, 2).forEach(System.out::println);

        System.out.println("\n");

        System.out.println("The common words are: ");
        main.findCommon(fileName1, fileName2);

    }

    public void findCommon(String txt1, String txt2){

        try {
            List arrList = Files.lines(Paths.get(txt1)).flatMap(str -> Arrays.stream(str.split(" "))).collect(Collectors.toList());


            Files.lines(Paths.get(txt2)).flatMap(str -> Arrays.stream(str.split(" ")))
                    .filter(arrList::contains)
                    .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Stream<String> getLongestWords(String txt, int n){
        try {
            return Files.lines(Paths.get(txt))
                    .flatMap(str -> Arrays.stream(str.split(" ")))
                    .sorted((word1, word2) -> word2.length() - word1.length())
                    .limit(n);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String findFirstWordLonger(String txt, int n){

        try {
            return Files.lines(Paths.get(txt))
                    .flatMap(str -> Arrays.stream(str.split(" ")))
                    .filter(str -> str.length() >= n)
                    .findFirst()
                    .get();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public long getNumberWords(String txt) {

        try {
              return Files.lines(Paths.get(txt))
                      .flatMap(str -> Arrays.stream(str.split(" ")))
                      .count();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;

    }
}
