package com.splitwise.backend.command;

import org.springframework.stereotype.Component;

@Component
public class AddExpense implements Command{
    @Override
    public boolean matches(String[] command) {
        if(command.length >= 1 && command[0].equals("addExpense")) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String[] command) {
        String expenseName = command[1];
        String discription = command[2];
        String amount = command[3];
        //take other data from command & create parameters to pass to controller
        //call the controller
    }
}
