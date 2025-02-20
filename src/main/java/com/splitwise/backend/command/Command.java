package com.splitwise.backend.command;

public interface Command {
    boolean matches(String[] command);
    void execute(String[] command);
}
