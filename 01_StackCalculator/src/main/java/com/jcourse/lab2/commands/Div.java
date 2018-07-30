package com.jcourse.lab2.commands;

import java.util.Stack;

public class Div implements Command {
    private Stack<Double> stack;
    private String[] args;

    public Div(Stack<Double> stack, String[] args) {
        this.stack = stack;
        this.args = args;
    }

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