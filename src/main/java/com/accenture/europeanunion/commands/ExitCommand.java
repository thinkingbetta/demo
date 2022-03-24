package com.accenture.europeanunion.commands;

import com.accenture.europeanunion.entities.User;
import com.accenture.salsasyntax.flowers.Commandos.ExitCommando;

import java.util.ArrayList;

public class ExitCommand extends Command{
    private final String commandName = "exit";
    private final boolean value = true;
    private ArrayList<User> users;

    public ExitCommand (ArrayList<User> users){
        this.users = users;
    }


    @Override
    public boolean run() {

        System.out.println(users.get(users.size()-1).getName() + ", see you next time!");
        return value;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
