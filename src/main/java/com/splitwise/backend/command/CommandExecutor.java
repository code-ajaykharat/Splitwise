package com.splitwise.backend.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandExecutor {//factory
    private List<Command> commands;
    private SettleUpGroup settleUpGroup;

    @Autowired
    public CommandExecutor(SettleUpGroup settleUpGroup){
        this.commands = new ArrayList<>();
        this.settleUpGroup = settleUpGroup;
        commands.add(settleUpGroup);
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void removeCommand(Command command) {
        commands.remove(command);
    }

    public void executeCommand(String[] command){
        for(Command c: commands){
            if(c.matches(command)){
                c.execute(command);
                break;
            }
        }
    }

}
