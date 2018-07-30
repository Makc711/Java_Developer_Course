package com.jcourse.lab2.commands;

import org.junit.Test;
import java.util.Stack;
import static org.junit.Assert.*;

public class SqrtTest {

    @Test
    public void execute() {
        String command = "SQRT";
        String[] args = command.split(" ");
        Stack<Double> stack = new Stack<>();
        stack.push(9.0);

        Command cmd = new Sqrt(stack, args);
        cmd.execute();

        Double result = stack.peek();
        Double expected = 3.0;
        assertEquals(expected, result);
    }
}