package com.codespeaks.hexagonal.adapter.console;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.codespeaks.hexagonal.domain.Customer;
import com.codespeaks.hexagonal.exception.CustomerNotFoundException;
import com.codespeaks.hexagonal.service.CustomerService;
import com.google.inject.Inject;

public class CommandInterfaceImpl implements CommandInterface {

    private CustomerService customerService;

    @Inject
    public CommandInterfaceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void list(Scanner scanner) {
        //System.out.println("All customers registered so far:");
        System.out.println("------------------------------------");

        List<Customer> customers = customerService.getAllCustomers();

        customers.stream()
            .forEach(c -> {
                CommandConsoleUtils.printCustomer(c);
                System.out.println("------------------------------------");
            });

    }

    public void register(Scanner scanner) {
        System.out.println("What is the first name?");
        String firstName = CommandConsoleUtils.readString(scanner);

        System.out.println("What is the last name?");
        String lastName = CommandConsoleUtils.readString(scanner);

        Customer unregistered = new Customer();
        unregistered.setFirstName(firstName);
        unregistered.setLastName(lastName);
        Customer registered = customerService.registerCustomer(unregistered);

        System.out.println("Customer registered successfully:");
        CommandConsoleUtils.printCustomer(registered);

    }

    public void upgrade(Scanner scanner) {
        System.out.println("What is the Customer Id you want to upgrade?");
        String customerId = CommandConsoleUtils.readString(scanner);
        Optional<Customer> customerWrapper = customerService.findCustomerById(Integer.valueOf(customerId));

        if (customerWrapper.isPresent()) {
            System.out.println("The Customer you want to upgrade is:");
            CommandConsoleUtils.printCustomer(customerWrapper.get());
            System.out.println("Do you want to proceed? Y(es)/N(o)");
            String confirm = CommandConsoleUtils.readString(scanner);

            if (confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y")) {
                try {
                    Customer customer = customerService.upgradeCustomer(customerWrapper.get());
                    System.out.println("Customer upgrade succeeded:");
                    CommandConsoleUtils.printCustomer(customer);
                } catch (CustomerNotFoundException e) {
                    System.out.println("Customer upgrade failed.");
                }

            }
        } else {
            System.out.println("Can't find the customer with supplied Id. Please try again.");
        }

    }

    public void downgrade(Scanner scanner) {
        System.out.println("What is the Customer Id you want to downgrade?");
        String customerId = CommandConsoleUtils.readString(scanner);
        Optional<Customer> customerWrapper = customerService.findCustomerById(Integer.valueOf(customerId));

        if (customerWrapper.isPresent()) {
            System.out.println("The Customer you want to downgrade is:");
            CommandConsoleUtils.printCustomer(customerWrapper.get());
            System.out.println("Do you want to proceed ? Y(es)/N(o)");
            String confirm = CommandConsoleUtils.readString(scanner);

            if (confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y")) {
                try {
                    Customer customer = customerService.downgradeCustomer(customerWrapper.get());
                    System.out.println("Customer downgrade succeeded:");
                    CommandConsoleUtils.printCustomer(customer);
                } catch (CustomerNotFoundException e) {
                    System.out.println("Customer downgrade failed.");
                }

            }
        } else {
            System.out.println("Can't find the customer with supplied Id. Please try again.");
        }

    }

    public void info() {
        CommandConsoleUtils.printMainMenu();
    }

}
