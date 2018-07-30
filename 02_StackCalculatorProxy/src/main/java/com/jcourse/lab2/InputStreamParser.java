package com.jcourse.lab2;

import com.jcourse.lab2.commands.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class InputStreamParser {
    private static Stack<Double> stack = new Stack<>();

    public static Stack<Double> getStack() {
        return stack;
    }

    public static ArrayList<Command> scannedCommands(Scanner scanner) {
        ArrayList<Command> commands = new ArrayList<>();
        Map<String, Double> variablesMap = new HashMap<>();

        Properties properties = new Properties();
        InputStream stream = InputStreamParser.class.getClassLoader().getResourceAsStream("Commands.properties");
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            String[] args = scanner.nextLine().split(" ");
            if (args[0].equals("EXECUTE")){
                break;
            }

            String fullNameOfClass = properties.getProperty(args[0]);
            Class<?> classOfCommand;
            try {
                classOfCommand = Class.forName(fullNameOfClass);
            } catch (Exception e) {
                System.out.println("Error \"" + args[0] + "\": Command not found!");
                continue;
            }

            Constructor<?>[] declaredConstructors = classOfCommand.getDeclaredConstructors();
            Constructor<?> declaredConstructor = declaredConstructors[0];
            Command cmd = null;
            try {
                cmd = (Command) declaredConstructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

            Field[] fields = classOfCommand.getDeclaredFields();
            if (fields != null && cmd != null) {
                for (Field field : fields) {
                    if (field.isAnnotationPresent(In.class)) {
                        In annotation = field.getAnnotation(In.class);
                        TypeOfArgs annotationType = annotation.type();

                        switch (annotationType) {
                            case STACK:
                                field.setAccessible(true);
                                try {
                                    field.set(cmd, stack);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case ARGS:
                                field.setAccessible(true);
                                try {
                                    field.set(cmd, args);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case DEFINITIONS:
                                field.setAccessible(true);
                                try {
                                    field.set(cmd, variablesMap);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                                break;
                            default:
                                throw new RuntimeException("Unexpected annotation: " + annotationType);
                        }
                    }
                }

                commands.add(cmd);
            }
        }
        return commands;
    }
}
