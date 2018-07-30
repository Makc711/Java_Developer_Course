package com.jcourse.lab2.commands;

import java.util.Map;
import java.util.Stack;

public class Define implements Command {
    @In(type = TypeOfArgs.STACK)
    private Stack<Double> stack;
    @In(type = TypeOfArgs.ARGS)
    private String[] args;
    @In(type = TypeOfArgs.DEFINITIONS)
    private Map<String, Double> definitions;

    @Override
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
