package com.jcourse.lab2;

import com.jcourse.lab2.commands.Command;
import com.jcourse.lab2.commands.LoggerOfCommands;

import java.io.*;
import java.lang.reflect.Proxy;
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
            for (Command command : commands){
                Command cmd = (Command) Proxy.newProxyInstance(
                        Command.class.getClassLoader(),
                        new Class[]{Command.class},
                        new LoggerOfCommands(command)
                );

                cmd.execute();
            }
        }
    }
}