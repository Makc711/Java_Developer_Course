package com.jcourse.lab2.commands;

public class Comment implements Command {
    private String[] args;

    public Comment(String[] args) {
        this.args = args;
    }

    public void execute() {
        for (String word : args){
            System.out.print(word + " ");
        }
        System.out.printf("%n");
    }
}
