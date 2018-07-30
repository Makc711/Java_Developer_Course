package com.jcourse.lab4.main;

class WordCounter implements Comparable<WordCounter> {
    private String name;
    private long freq;

    WordCounter(String name, long freq) {
        this.name = name;
        this.freq = freq;
    }

    String getName() { return name; }
    long getFreq() { return freq; }

    @Override
    public int compareTo(WordCounter wc) {
        return (freq >= wc.getFreq()) ? -1 : 1;
    }
}
