package com.codespeaks.hexagonal.adapter.console;

import java.util.Scanner;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class CommandClient {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new CommandConsoleModule());
        CommandInterface commandConsole = injector.getInstance(CommandInterface.class);
        CommandClient.start(commandConsole);

    }

    public static void start(CommandInterface commandConsole) {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String cmd = CommandConsoleUtils.readString(scanner);
            if ("ls".equalsIgnoreCase(cmd)) {
                commandConsole.list(scanner);
            } else if ("reg".equalsIgnoreCase(cmd)) {
                commandConsole.register(scanner);
            } else if ("ug".equalsIgnoreCase(cmd)) {
                commandConsole.upgrade(scanner);
            } else if ("dg".equalsIgnoreCase(cmd)) {
                commandConsole.downgrade(scanner);
            } else if ("info".equalsIgnoreCase(cmd)) {
                commandConsole.info();
            } else if ("exit".equalsIgnoreCase(cmd)) {
                exit = true;
            }
        }

    }

}
