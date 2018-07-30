package com.jcourse.lab2.commands;

import org.junit.Test;
import java.util.Stack;
import static org.junit.Assert.*;

public class DiffTest {

    @Test
    public void execute() {
        String command = "-";
        String[] args = command.split(" ");
        Stack<Double> stack = new Stack<>();
        stack.push(5.5);
        stack.push(1.3);

        Command cmd = new Diff(stack, args);
        cmd.execute();

        Double result = stack.peek();
        Double expected = 4.2;
        assertEquals(expected, result);
    }
}