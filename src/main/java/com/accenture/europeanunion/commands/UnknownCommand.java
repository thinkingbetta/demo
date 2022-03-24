package com.accenture.europeanunion.commands;

public class UnknownCommand extends Command{
    private String commandName = "unknown";
    private boolean value = false;

    @Override
    public boolean run() {
        System.out.println("Retry, maybe you will be lucky next time!");
        return value;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
