package com.jcourse.lab4.main;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try(Reader file = new InputStreamReader(
                new BufferedInputStream(
                        new FileInputStream(args[0])))) {
            Map<String, Long> wordFreq = getMapOfWordFreq(file);
            final long sumOfWords = wordFreq.entrySet().parallelStream().mapToLong(Map.Entry::getValue).sum();
            TreeSet<WordCounter> sortedWordFreq = new TreeSet<>();
            for (Map.Entry entry: wordFreq.entrySet()) {
                sortedWordFreq.add(new WordCounter((String) entry.getKey(), (Long) entry.getValue()));
            }
            createFile(sumOfWords, sortedWordFreq);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void createFile(long sumOfWords, TreeSet<WordCounter> sortedWordFreq) throws IOException {
        PrintWriter out = new PrintWriter(
                new OutputStreamWriter(
                        new FileOutputStream("sorting_words.csv"), "cp1251"));
        for (WordCounter  wc : sortedWordFreq){
            out.write(wc.getName());
            out.write(";");
            out.print(wc.getFreq());
            out.write(";");
            out.println(100.0f * wc.getFreq() / sumOfWords + "%");
        }
        out.close();
    }

    private static Map<String, Long> getMapOfWordFreq(Reader file) throws IOException {
        StringBuilder sb = new StringBuilder();
        Map<String, Long> wordFreq = new HashMap<>();
        char[] buffer = new char[4096];
        while (file.read(buffer) != -1) {
            for (char c : buffer) {
                if (Character.isLetterOrDigit(c)) {
                    sb.append(c);
                } else if (sb.length() != 0){
                    wordFreq.merge(sb.toString(), 1L, (prev, initial) -> prev + initial);
                    sb.setLength(0);
                    if (c == '\0') break;
                }
            }
        }
        return wordFreq;
    }
}