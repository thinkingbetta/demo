package com.accenture.europeanunion.commands;

import com.accenture.europeanunion.entities.User;

import java.util.ArrayList;
import java.util.Scanner;

public class AddUserCommand extends Command {

    private final String commandName = "adduser";
    private final boolean value = false;
    private Scanner scanner;
    private ArrayList<User> users;

    public AddUserCommand (Scanner scanner, ArrayList<User> users ) {
        this.scanner = scanner;
        this.users = users;
    }

    @Override
    public boolean run() {

        String userName = scanner.nextLine();

        users.add(new User(userName));

        return value;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
