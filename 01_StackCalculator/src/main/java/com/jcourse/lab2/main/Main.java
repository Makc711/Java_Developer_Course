package com.jcourse.lab2.main;

import com.jcourse.lab2.commands.Command;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = null;
        if (args.length > 0) {
            try {
                File inputFile = new File(args[0]);
                scanner = new Scanner(inputFile);
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } else {
            scanner = new Scanner(System.in);
        }

        if (scanner != null) {
            ArrayList<Command> commands = InputStreamParser.scannedCommands(scanner);
            commands.forEach(Command::execute);
        }
    }
}