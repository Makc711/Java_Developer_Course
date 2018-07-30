package com.jcourse.lab2.commands;

import java.util.Stack;

public class Div implements Command {
    @In(type = TypeOfArgs.STACK)
    private Stack<Double> stack;
    @In(type = TypeOfArgs.ARGS)
    private String[] args;

    @Override
    public void execute() {
        if (CommandErrors.isCommandCorrectly(args, 1, stack, 2)){
            Double second = stack.pop();
            Double first = stack.pop();
            if (second != 0){
                stack.push(first / second);
            } else {
                System.out.println("Error \"" + args[0] + "\": Division by zero!");
            }
        }
    }
}