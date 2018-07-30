package com.jcourse.lab2.commands;

import java.util.Map;
import java.util.Stack;

public class Push implements Command {
    @In(type = TypeOfArgs.STACK)
    private Stack<Double> stack;
    @In(type = TypeOfArgs.ARGS)
    private String[] args;
    @In(type = TypeOfArgs.DEFINITIONS)
    private Map<String, Double> definitions;

    @Override
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