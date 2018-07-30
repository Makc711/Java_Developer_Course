package com.jcourse.lab2.commands;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Stack;
import com.jcourse.lab2.InputStreamParser;
import org.apache.log4j.Logger;

public class LoggerOfCommands implements InvocationHandler {
    final static Logger logger = Logger.getLogger(String.valueOf(LoggerOfCommands.class));

    private Stack<Double> stack;
    private Command obj;

    public LoggerOfCommands(Command command){
        stack = InputStreamParser.getStack();
        obj = command;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("Before: " + stack);
        logger.info("Command: " + obj.getClass().getSimpleName());
        Object result = method.invoke(obj, args);
        logger.info("After: " + stack);
        logger.info("-------------------------------");
        return result;
    }
}