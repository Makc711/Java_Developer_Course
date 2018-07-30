package com.jcourse.lab2.commands;

public class Comment implements Command {
    @In(type = TypeOfArgs.ARGS)
    private String[] args;

    @Override
    public void execute() {
        for (String word : args){
            System.out.print(word + " ");
        }
        System.out.printf("%n");
    }
}
