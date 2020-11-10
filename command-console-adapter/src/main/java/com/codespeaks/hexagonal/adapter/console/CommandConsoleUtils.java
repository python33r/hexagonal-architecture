package com.codespeaks.hexagonal.adapter.console;

import java.util.Scanner;

import com.codespeaks.hexagonal.domain.Customer;

public class CommandConsoleUtils {

    public static String readString(Scanner scanner) {
        System.out.print("> ");
        return scanner.nextLine();
    }

    public static void printCustomer(Customer c) {
        System.out.println("Customer Id: " + c.getCustomerId());
        System.out.println("Customer Name: " + c.getFirstName() +  " " + c.getLastName());
        System.out.println("Customer Points: " + c.getRewardPoints());
        System.out.println("Customer Status: " + c.getStatus());
    }

    public static void printMainMenu() {
        System.out.println("ls   - list all customers");
        System.out.println("reg  - register a customer");
        System.out.println("ug   - upgrade a customer");
        System.out.println("dg   - downgrade a customer");
        System.out.println("info - show main menu command");
        System.out.println("exit - exit application");
    }

}
