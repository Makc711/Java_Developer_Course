package com.jcourse.lab4StreamAPI.main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Paths.get(args[0]));
             PrintWriter writer = new PrintWriter(args[1])) {//hello hello, world goodbye 12abc, yyy
//            long[] wordCount = {0};
            AtomicLong wordCount = new AtomicLong(0);
            lines
                    .map(s -> s.split("[^\\pL\\pN]+"))//Stream<Array<String>>[hello, hello], [world, goodbye 12abc], [yyy]
                    .flatMap(Arrays::stream) //Stream<String> hello, hello, world, goodbye ...
                    .filter(s -> !s.isEmpty())
                    .peek(s -> wordCount.getAndIncrement())
                    .collect(Collectors.toMap(s -> s, s -> 1L, (freq1, freq2) -> freq1 + freq2)) //Map<String, Long> (hello, 2), (world, 1) ...
                    .entrySet()
                    .stream()//Stream<Map.Entry<String, Long>>
                    .sorted(Comparator
                            .comparingLong((Map.Entry<String, Long> entry) -> entry.getValue())
                            .reversed()
                            .thenComparing((Map.Entry<String, Long> entry) -> entry.getKey()))//Stream<Map.Entry<String, Long> (hello, 2), (goodbye, 1) ...
                    .forEach((Map.Entry<String, Long> entry) -> {
                        writer.println(entry.getKey() + "," + entry.getValue() + "," + (double) entry.getValue() / wordCount.get());
                    });


//            Map<String, Long> wordFreq = getMapOfWordFreq(file);
//            final long sumOfWords = wordFreq.entrySet().parallelStream().mapToLong(Map.Entry::getValue).sum();
//            TreeSet<WordCounter> sortedWordFreq = new TreeSet<>();
//            for(Map.Entry entry: wordFreq.entrySet()) {
//                sortedWordFreq.add(new WordCounter((String) entry.getKey(), (Long) entry.getValue()));
//            }
//            createFile(sumOfWords, sortedWordFreq);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

//    private static void createFile(long sumOfWords, TreeSet<WordCounter> sortedWordFreq) throws IOException {
//        PrintWriter out = new PrintWriter(
//                new OutputStreamWriter(
//                        new FileOutputStream("sorting_words.csv"), "cp1251"));
//        for(WordCounter  wc : sortedWordFreq){
//            out.write(wc.getName());
//            out.write(";");
//            out.print(wc.getFreq());
//            out.write(";");
//            out.println(100.0f * wc.getFreq() / sumOfWords + "%");
//        }
//        out.close();
//    }
//
//    private static Map<String, Long> getMapOfWordFreq(Reader file) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        Map<String, Long> wordFreq = new HashMap<>();
//        char[] buffer = new char[4096];
//        while (file.read(buffer) != -1) {
//            for (char c : buffer) {
//                if (Character.isLetterOrDigit(c)) {
//                    sb.append(c);
//                } else if (sb.length() != 0){
//                    wordFreq.merge(sb.toString(), 1L, (prev, initial) -> prev + initial);
//                    sb.setLength(0);
//                    if (c == '\0') break;
//                }
//            }
//        }
//        return wordFreq;
//    }
}