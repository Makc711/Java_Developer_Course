package com.jcourse.lab2.commands;

import java.util.Stack;

class CommandErrors {
    static boolean isCommandCorrectly(String[] args, int numberOfArgs, Stack<Double> stack, int stackSize){
        boolean isNotError = false;
        if (args.length != numberOfArgs) {
            System.out.println("Error \"" + args[0] + "\": Wrong number of arguments in the command!");
        } else if (stack.size() < stackSize) {
            System.out.println("Error \"" + args[0] + "\": Not enough elements in the stack! (Stack = " + stack.size() + ")");
        } else {
            isNotError = true;
        }
        return isNotError;
    }
}