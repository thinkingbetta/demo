package com.accenture.europeanunion.commands;

import java.util.ArrayList;
import java.util.Scanner;

public class HowAreYouCommand extends Command{
    private final boolean value = false;
    private final String commandName = "howareyou";
    private Scanner scanner;

    public HowAreYouCommand(Scanner scanner){
        this.scanner = scanner;
    }

    @Override
    public boolean run() {
        System.out.println("How are you feeling today?");
        String feeling = "fine";//scanner.nextLine().toLowerCase();
        ArrayList<String> happyFeelings = new ArrayList<>();
        happyFeelings.add("fine");
        happyFeelings.add("happy");
        happyFeelings.add("fantastic");
        if (happyFeelings.contains(feeling)) {
            System.out.println("Happy to read that!");
        }

        ArrayList<String> notHappyFeelings = new ArrayList<>();
        notHappyFeelings.add("sad");
        notHappyFeelings.add("angry");
        notHappyFeelings.add("ill");
        notHappyFeelings.add("bad");
        if (notHappyFeelings.contains(feeling)) System.out.println("Very sorry to read that!");

        return value;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
