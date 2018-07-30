package com.jcourse.lab2.commands;

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import static org.junit.Assert.*;

public class DefineTest {

    @Test
    public void execute() {
        String command = "DEFINE a 12.3";
        String[] args = command.split(" ");
        Stack<Double> stack = new Stack<>();
        Map<String, Double> definitions = new HashMap<>();

        Command cmd = new Define(stack, args, definitions);
        cmd.execute();

        Double result = definitions.get(args[1]);
        Double expected = 12.3;
        assertEquals(expected, result);
    }
}