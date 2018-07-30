package com.jcourse.lab2.commands;

import java.util.Stack;

public class Diff implements Command {
    private Stack<Double> stack;
    private String[] args;

    public Diff(Stack<Double> stack, String[] args) {
        this.stack = stack;
        this.args = args;
    }

    public void execute() {
        if (CommandErrors.isCommandCorrectly(args, 1, stack, 2)){
            Double second = stack.pop();
            Double first = stack.pop();
            stack.push(first - second);
        }
    }
}