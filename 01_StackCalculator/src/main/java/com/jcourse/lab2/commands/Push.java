package com.jcourse.lab2.commands;

import java.util.Map;
import java.util.Stack;

public class Push implements Command {
    private Stack<Double> stack;
    private String[] args;
    private Map<String, Double> definitions;

    public Push(Stack<Double> stack, String[] args, Map<String, Double> definitions) {
        this.stack = stack;
        this.args = args;
        this.definitions = definitions;
    }

    public void execute() {
        if (CommandErrors.isCommandCorrectly(args, 2, stack, 0)){
            try {
                stack.push(Double.parseDouble(args[1]));
            } catch (NumberFormatException e){
                if (definitions.containsKey(args[1])){
                    stack.push(definitions.get(args[1]));
                } else {
                    System.out.println("Error \"PUSH\": Key \"" + args[1] + "\" does not exist!");
                }
            }
        }
    }
}