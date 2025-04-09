package com.splitwise.backend.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Order(1) //to run this class first
public class MyCommandLineRunner implements CommandLineRunner {
    private Scanner scanner = new Scanner(System.in);
    private CommandExecutor commandExecutor; //command registry

    @Autowired
    public MyCommandLineRunner(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean exit = true;
        while (exit) {
            System.out.println("Enter a command: ");
            String command = scanner.nextLine();
            String[] commandParts = command.split(" "); //returns an array of strings
            if (command.equals("exit")) {
                return;
            }

            commandExecutor.executeCommand(commandParts);
        }
    }
}
