package com.accenture.europeanunion.commands;

import com.accenture.europeanunion.entities.Country;

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
        ArrayList<Country> countries = readCountriesFromDatabase();

        for (Country c : countries) {
            System.out.println(c.toString());
        }
        return value;
    }



    private ArrayList<Country> readCountriesFromDatabase() throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("select * from country");
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Country> countries = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String countryName = resultSet.getString("country_name");
            String capitalName = resultSet.getString("country_capital");
            int countryPopulation = resultSet.getInt("country_population");

           // System.out.println("id" + id + " country " + countryName + " capital " + capitalName);

            Country country = new Country(countryName,capitalName, null, null, countryPopulation,0);
            countries.add(country);

        }
        return countries;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
