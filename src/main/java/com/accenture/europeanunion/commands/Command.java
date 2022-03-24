package com.accenture.europeanunion.commands;

public abstract class Command {

    private String commandName;

    public abstract boolean run();

    public abstract String getCommandName();
}
