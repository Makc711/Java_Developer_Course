package com.jcourse.lab2.main;

import com.jcourse.lab2.commands.*;
import java.util.*;

class InputStreamParser {
    private static Stack<Double> stack = new Stack<>();

    static Stack<Double> getStack() {
        return stack;
    }

    static ArrayList<Command> scannedCommands(Scanner scanner) {
        ArrayList<Command> commands = new ArrayList<>();
        Map<String, Double> variablesMap = new HashMap<>();

        while (scanner.hasNextLine()) {
            String[] args = scanner.nextLine().split(" ");
            Command cmd = null;

            switch (args[0]) {
                case "DEFINE":
                    cmd = new Define(stack, args, variablesMap);
                    break;
                case "PUSH":
                    cmd = new Push(stack, args, variablesMap);
                    break;
                case "POP":
                    cmd = new Pop(stack, args);
                    break;
                case "+":
                    cmd = new Sum(stack, args);
                    break;
                case "-":
                    cmd = new Diff(stack, args);
                    break;
                case "*":
                    cmd = new Multiply(stack, args);
                    break;
                case "/":
                    cmd = new Div(stack, args);
                    break;
                case "SQRT":
                    cmd = new Sqrt(stack, args);
                    break;
                case "PRINT":
                    cmd = new Print(stack, args);
                    break;
                case "#":
                    cmd = new Comment(args);
                    break;
                case "EXECUTE":
                    return commands;
                default:
                    System.out.println("Error \"" + args[0] + "\": Command not found!");
            }

            if (cmd != null) {
                commands.add(cmd);
            }
        }
        return commands;
    }
}
