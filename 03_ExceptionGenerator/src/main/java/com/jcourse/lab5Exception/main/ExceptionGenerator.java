package com.jcourse.lab5Exception.main;

public interface ExceptionGenerator {
    void generateNullPointerException();
    void generateClassCastException();
    void generateNumberFormatException();
    void generateStackOverflowError();
    void generateOutOfMemoryError();
    void generateMyException(String message) throws MyException;
}
