package com.jcourse.lab5Exception.main;

public class ExceptionGeneratorImpl implements ExceptionGenerator {
    @SuppressWarnings("all")
    @Override
    public void generateNullPointerException() {
        Object obj = null;
        obj.getClass();
    }

    @SuppressWarnings("unused")
    @Override
    public void generateClassCastException() {
        Integer i = (Integer)(Object)"s";
    }

    @SuppressWarnings("all")
    @Override
    public void generateNumberFormatException() {
        Integer.valueOf("s");
    }

    @SuppressWarnings("all")
    @Override
    public void generateStackOverflowError() {
        generateStackOverflowError();
    }

    @SuppressWarnings("unused")
    @Override
    public void generateOutOfMemoryError() {
        int[] overflow = new int[Integer.MAX_VALUE];
    }

    @Override
    public void generateMyException(String message) throws MyException {
        throw new MyException(message);
    }
}
