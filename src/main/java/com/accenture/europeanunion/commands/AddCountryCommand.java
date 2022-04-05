package com.accenture.europeanunion.commands;


import com.accenture.europeanunion.entities.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AddCountryCommand extends Command {
    private final String commandName= "addcountry";
    private Scanner scanner;
    private Connection connection;
    private final boolean value = false;

    public AddCountryCommand(Scanner scanner, Connection connection){
        if(connection == null){
            throw  new IllegalArgumentException("Connection must not be null");
        }
        this.scanner = scanner;
        this.connection = connection;
    }

    @Override
    public boolean run() throws SQLException {
//TODO alla fine chiedere se tutto e' corretto, se si mandare avanti a "What do you want to do?" altrimenti riscrivere cio' che e' stato scritto nella Array<> List
        System.out.println("What is the name of your country?");
        String countryName = scanner.nextLine();
        //TODO se il nome della country e' gia' inserito nel database allora dare errore
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

        Country country = new Country(countryName,capitalName, language,greeting,population, entranceYear);

        int countryID = createCountry(country);

        //countries.add(new Country(countryName, capitalName, language, greeting, population, entranceYear));


        System.out.println("The following was added");
        //TODO cotruire una toString prendendo tutti i dati relativi all'ultima country aggiunta, magari e' utile la countryID generata
        System.out.println(country.toString());

        return value;
    }

    private int createCountry(Country country) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO country (country_name, country_capital, country_population) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
        PreparedStatement preparedStatementLanguage = connection.prepareStatement("INSERT INTO language (language_name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1,country.getCountryName());
        preparedStatement.setString(2, country.getCapitalCity());
        preparedStatement.setInt(3, country.getPopulation());
        preparedStatement.executeUpdate();

        preparedStatementLanguage.setString(1, country.getLanguage());
        preparedStatementLanguage.executeUpdate();

        PreparedStatement preparedStatementLanguageCountry = connection.prepareStatement("INSERT INTO country_language (country_id, language_id) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);



        ResultSet generatedKeysCountry = preparedStatement.getGeneratedKeys();
        generatedKeysCountry.next();
        int generatedKeyCountry = generatedKeysCountry.getInt(1);

        ResultSet generatedKeysLanguage= preparedStatementLanguage.getGeneratedKeys();
        generatedKeysLanguage.next();
        int generatedKeysLanguage1 = generatedKeysLanguage.getInt(1);

        preparedStatementLanguageCountry.setInt(1,generatedKeyCountry );
        preparedStatementLanguageCountry.setInt(2,generatedKeysLanguage1);
        preparedStatementLanguageCountry.executeUpdate();

        System.out.println("my generated key is " + generatedKeyCountry);

        return generatedKeyCountry;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
