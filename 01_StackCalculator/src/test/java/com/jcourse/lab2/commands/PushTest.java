package com.jcourse.lab2.commands;

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static org.junit.Assert.*;

public class PushTest {

    @Test
    public void execute() {
        String command = "PUSH 12.3";
        String[] args = command.split(" ");
        Stack<Double> stack = new Stack<>();
        Map<String, Double> definitions = new HashMap<>();

        Command cmd = new Push(stack, args, definitions);
        cmd.execute();

        Double result = stack.peek();
        Double expected = 12.3;
        assertEquals(expected, result);
    }
}