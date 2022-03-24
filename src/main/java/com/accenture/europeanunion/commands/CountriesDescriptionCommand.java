package com.accenture.europeanunion.commands;

import com.accenture.europeanunion.entities.Country;

import java.util.ArrayList;

public class CountriesDescriptionCommand extends Command{

    private final boolean value = false;
    private final String commandName = "countriesdescription";
    private ArrayList<Country> countries;

    public CountriesDescriptionCommand(ArrayList<Country> countries){
        this.countries = countries;
    }

    @Override
    public boolean run() {
        for (Country c : countries) {
            System.out.println(c.getPopularGreetings() + ", welcome to " + c.getCountryName() + "! " + "\nThe capital is " + c.getCapitalCity() + ". \nThe official language is " + c.getLanguage() + " and the population is about " + c.getPopulation() + " people.\n" + c.getCountryName() + " entered the European Union in " + c.getEntranceYear() + "\n" );
        }
        return value;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
