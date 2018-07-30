package com.jcourse.lab5Exception.main;

import java.io.Serializable;

public class Main implements Serializable {
    public static void main(String[] args) {
        ExceptionGenerator generator = new ExceptionGeneratorImpl();
        try{
            generator.generateNullPointerException();
        } catch (NullPointerException e){
            System.out.println("NPE catched!");
            //e.printStackTrace();
        }

        try{
            generator.generateClassCastException();
        } catch (ClassCastException e){
            System.out.println("CCE catched!");
            //e.printStackTrace();
        }

        try{
            generator.generateNumberFormatException();
        } catch (NumberFormatException e){
            System.out.println("NFE catched!");
            //e.printStackTrace();
        }

        try{
            generator.generateStackOverflowError();
        } catch (StackOverflowError e){
            System.out.println("SOE catched!");
            //e.printStackTrace();
        }

        try{
            generator.generateOutOfMemoryError();
        } catch (OutOfMemoryError e){
            System.out.println("OoME catched!");
            //e.printStackTrace();
        }

        try{
            generator.generateMyException("My Exception");
        } catch (MyException e){
            System.out.println("ME catched!");
            //e.printStackTrace();
        }
    }
}
