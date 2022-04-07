package com.accenture.europeanunion.commands;

import com.accenture.europeanunion.entities.Country;
import com.accenture.europeanunion.services.CountryService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CountriesDescriptionCommand extends Command{

    private final boolean value = false;
    private final String commandName = "countriesdescription";
    private Connection connection;




    public CountriesDescriptionCommand(Connection connection){
        this.connection = connection;
    }


    @Override
    public boolean run() throws SQLException {
        CountryService countryService = new CountryService(connection);
        ArrayList<Country> countries = countryService.readCountriesFromDatabase();

        for (Country c : countries) {
            System.out.println(c.toString());
        }
        return value;
    }



    @Override
    public String getCommandName() {
        return commandName;
    }
}
