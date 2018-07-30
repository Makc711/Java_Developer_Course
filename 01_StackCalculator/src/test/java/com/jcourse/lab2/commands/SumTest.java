package com.jcourse.lab2.commands;

import org.junit.Test;
import java.util.Stack;
import static org.junit.Assert.*;

public class SumTest {

    @Test
    public void execute() {
        String command = "+";
        String[] args = command.split(" ");
        Stack<Double> stack = new Stack<>();
        stack.push(9.0);
        stack.push(3.0);

        Command cmd = new Sum(stack, args);
        cmd.execute();

        Double result = stack.peek();
        Double expected = 12.0;
        assertEquals(expected, result);
    }
}