package com.splitwise.backend.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CommandExecutor {//factory
    private Map<String, Command> commands;

    @Autowired
    public CommandExecutor(Map<String, Command> cmds) { //spring automatically injects all the beans of type Command (key is the name of the bean)
        this.commands = cmds;
    }

    public void executeCommand(String[] command) {
        String commandName = command[0];
        Command cmd = commands.get(commandName);
        if (cmd != null) {
            cmd.execute(command);
        } else {
            System.out.println("Command not found");
        }
    }

}
