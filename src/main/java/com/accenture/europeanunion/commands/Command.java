package com.accenture.europeanunion.commands;

import java.sql.SQLException;

public abstract class Command {

    private String commandName;

    public abstract boolean run() throws SQLException;

    public abstract String getCommandName();
}
