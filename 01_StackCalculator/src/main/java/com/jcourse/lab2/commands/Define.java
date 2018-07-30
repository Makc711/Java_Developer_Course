package com.jcourse.lab2.commands;

import java.util.Map;
import java.util.Stack;

public class Define implements Command {
    private Stack<Double> stack;
    private String[] args;
    private Map<String, Double> definitions;

    public Define(Stack<Double> stack, String[] args, Map<String, Double> definitions) {
        this.stack = stack;
        this.args = args;
        this.definitions = definitions;
    }

    public void execute() {
        if (CommandErrors.isCommandCorrectly(args, 3, stack, 0)){
            if (Character.isDigit(args[1].charAt(0))) {
                System.out.println("Error \"DEFINE\": The first character of a variable can not be a digit!");
            } else {
                definitions.put(args[1], Double.parseDouble(args[2]));
            }
        }
    }
}
