package com.jcourse.lab2.main;

import com.jcourse.lab2.commands.Command;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void calcTest() {
        File inputFile = new File("src\\test\\java\\resources\\commands");
        Scanner scanner = null;
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (scanner != null) {
            ArrayList<Command> commands = InputStreamParser.scannedCommands(scanner);
            commands.forEach(Command::execute);
        }

        Stack<Double> stack = InputStreamParser.getStack();
        Double result = stack.peek();
        Double expected = -0.5857864376269049;
        assertEquals(expected, result);
        assertEquals(1, stack.size());
    }
}