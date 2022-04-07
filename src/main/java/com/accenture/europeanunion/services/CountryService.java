package com.accenture.europeanunion.services;

import com.accenture.europeanunion.entities.Country;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CountryService {
    private Connection connection;


    public CountryService(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Country> readCountriesFromDatabase() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT c.id, c.country_name, c.country_capital, c.country_population, c.country_greeting, \n" +
                "l.language_name, y.year\n" +
                "FROM country c\n" +
                "LEFT JOIN country_language s\n" +
                "ON c.id = s.country_id\n" +
                "LEFT JOIN language l\n" +
                "ON s.language_id = l.id\n" +
                "LEFT JOIN year_country p\n" +
                "on c.id = p.country_id\n" +
                "LEFT JOIN year y \n" +
                "on p.year_id=y.id;");
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Country> countries = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("c.id");
            String countryName = resultSet.getString("c.country_name");
            String capitalName = resultSet.getString("c.country_capital");
            int countryPopulation = resultSet.getInt("c.country_population");
            String countryGreeting = resultSet.getString("c.country_greeting");
            String countryLanguage = resultSet.getString("l.language_name");
            int entranceYear = resultSet.getInt("y.year");


            Country country = new Country(countryName, capitalName, countryLanguage, countryGreeting, countryPopulation, entranceYear);
            countries.add(country);

        }
        return countries;

    }

/*
    public String toString(Country country){
        return "Country name: " + country.getCountryName() + "\nCapital city: " + country.getCapitalCity() +
                "\nLanguage:" + country.getLanguage()+ "\nPopular Greeting: " + country.getPopularGreetings() +
                "\nPopulation: " + country.getPopulation() + "\nEntrance year: " + country.getEntranceYear() + "\n" ;

    }
*/

}