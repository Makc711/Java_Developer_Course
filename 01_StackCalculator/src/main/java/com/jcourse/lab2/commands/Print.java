package com.jcourse.lab2.commands;

import java.util.Stack;

public class Print implements Command {
    private Stack<Double> stack;
    private String[] args;

    public Print(Stack<Double> stack, String[] args) {
        this.stack = stack;
        this.args = args;
    }

    public void execute() {
        if (CommandErrors.isCommandCorrectly(args, 1, stack, 1)){
            System.out.println(stack.peek());
        }
    }
}
