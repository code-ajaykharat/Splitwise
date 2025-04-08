package com.splitwise.backend.command;

public interface Command {
    boolean matches(String[] command); //Optional
    void execute(String[] command);
}
