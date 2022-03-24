package com.accenture.europeanunion.commands;


import com.accenture.europeanunion.entities.Country;

import java.util.ArrayList;
import java.util.Scanner;

public class AddCountryCommand extends Command {
    private final String commandName= "addcountry";
    private Scanner scanner;
    private ArrayList<Country> countries;
    private final boolean value = false;

    public AddCountryCommand(Scanner scanner, ArrayList<Country> countries){
        this.scanner = scanner;
        this.countries = countries;
    }

    @Override
    public boolean run() {
//TODO alla fine chiedere se tutto e' corretto, se si mandare avanti a "What do you want to do?" altrimenti riscrivere cio' che e' stato scritto nella Array<> List
        System.out.println("What is the name of your country?");
        String countryName = scanner.nextLine();
        System.out.println("What is the name of the capital?");
        String capitalName = scanner.nextLine();
        System.out.println("Which is the official language?");
        String language = scanner.nextLine();
        System.out.println("Which is the most common greeting?");
        String greeting = scanner.nextLine();
        System.out.println("How many people live here?");
        int population = Integer.parseInt(scanner.nextLine());
        System.out.println("When did it become part of the European Union?");
        int entranceYear = Integer.parseInt(scanner.nextLine());

        countries.add(new Country(countryName, capitalName, language, greeting, population, entranceYear));


        System.out.println("The following was added");
        System.out.println(countries.get(countries.size()-1).toString());

        return value;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
