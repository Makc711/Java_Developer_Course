package com.jcourse.lab2.commands;

import java.util.Stack;

public class Exp implements Command {
    @In(type = TypeOfArgs.STACK)
    private Stack<Double> stack;
    @In(type = TypeOfArgs.ARGS)
    private String[] args;

    @Override
    public void execute() {
        if (CommandErrors.isCommandCorrectly(args, 1, stack, 1)){
            stack.push(Math.exp(stack.pop()));
        }
    }
}