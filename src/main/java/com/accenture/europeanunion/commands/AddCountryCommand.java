package com.accenture.europeanunion.commands;


import com.accenture.europeanunion.CLI.ScannerFormatting;
import com.accenture.europeanunion.entities.Country;

import java.sql.*;
import java.util.Scanner;

public class AddCountryCommand extends Command {
    private final String commandName= "addcountry";
    private Scanner scanner;
    private Connection connection;
    private final boolean value = false;
    private ScannerFormatting scannerFormatting;

    public AddCountryCommand(Scanner scanner, Connection connection, ScannerFormatting scannerFormatting){
        this.scannerFormatting = scannerFormatting;
        if(connection == null){
            throw  new IllegalArgumentException("Connection must not be null");
        }
        this.scanner = scanner;
        this.connection = connection;
    }

    @Override
    public boolean run() throws SQLException {
        System.out.println("What is the name of your country?");
        String countryName = scannerFormatting.getFormattedString(scanner);

        PreparedStatement getStatement = connection.prepareStatement("SELECT id FROM country WHERE country_name = ?");
        getStatement.setString(1, countryName);
        ResultSet countryNameResult = getStatement.executeQuery();

        if (countryNameResult.next()) {
            throw new IllegalArgumentException(countryName + " already existing, enter another country!");
        } else {
            System.out.println("What is the name of the capital?");
            String capitalName = scannerFormatting.getFormattedString(scanner);
            System.out.println("Which is the official language?");
            String language = scannerFormatting.getFormattedString(scanner);
            System.out.println("Which is the most common greeting?");
            String greeting = scannerFormatting.getFormattedString(scanner);
            System.out.println("How many people live here?");
            int population = scannerFormatting.getFormattedInt(scanner);
            System.out.println("When did it become part of the European Union?");
            int entranceYear = scannerFormatting.getFormattedInt(scanner);

            System.out.println(countryName + " " + capitalName + " " + language + " " + greeting + " "  + population + " " + entranceYear +
                    "\nIs everything correct?[true or false]");
            Boolean input = Boolean.valueOf(scanner.nextLine().trim().toLowerCase());
            if (input.equals(true)) {
                Country country = new Country(countryName, capitalName, language, greeting, population, entranceYear);

                int countryID = createCountry(country);

                System.out.println("The following was added:\n" + country);
            } else {
                System.out.println("Country was not added.");
            }
        }
        return value;
    }




    private int createCountry(Country country) throws SQLException {


            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO country (country_name," +
                    " country_capital, country_population, country_greeting) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, country.getCountryName());
            preparedStatement.setString(2, country.getCapitalCity());
            preparedStatement.setInt(3, country.getPopulation());
            preparedStatement.setString(4, country.getPopularGreetings());
            preparedStatement.executeUpdate();

            ResultSet generatedKeysCountry = preparedStatement.getGeneratedKeys();
            generatedKeysCountry.next();
            int generatedKeyCountry = generatedKeysCountry.getInt(1);

            System.out.println("my generated key is " + generatedKeyCountry);

            createLanguageCountry(country, generatedKeyCountry);

            createYearCountry(country, generatedKeyCountry);


        return generatedKeyCountry;
    }

    private void createYearCountry(Country country, int generatedKeyCountry) throws SQLException {
        int year = country.getEntranceYear();

        PreparedStatement getStatementYear = connection.prepareStatement("SELECT id FROM year WHERE year = ?");
        getStatementYear.setInt(1, year);
        ResultSet yearResult = getStatementYear.executeQuery();

        if (yearResult.next()) {
            PreparedStatement preparedStatementYearCountry = connection.prepareStatement("INSERT INTO year_country" +
                    " (year_id, country_id) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatementYearCountry.setInt(1, yearResult.getInt(1));
            preparedStatementYearCountry.setInt(2, generatedKeyCountry);
            preparedStatementYearCountry.executeUpdate();

        } else {PreparedStatement preparedStatementYear = connection.prepareStatement("INSERT INTO year (year)" +
                    " VALUES(?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatementYear.setInt(1, country.getEntranceYear());
            preparedStatementYear.executeUpdate();

            PreparedStatement preparedStatementYearCountry = connection.prepareStatement("INSERT INTO year_country" +
                    " (year_id, country_id) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);

            ResultSet generatedKeysYear = preparedStatementYear.getGeneratedKeys();
            generatedKeysYear.next();
            int generatedKeyYear = generatedKeysYear.getInt(1);

            preparedStatementYearCountry.setInt(1, generatedKeyYear);
            preparedStatementYearCountry.setInt(2, generatedKeyCountry);
            preparedStatementYearCountry.executeUpdate();
        }
    }
//TODO come aggiungere piu' lingue?
    private void createLanguageCountry(Country country, int generatedKeyCountry) throws SQLException {
        String language = country.getLanguage();

        PreparedStatement getStatementYear = connection.prepareStatement("SELECT id FROM language WHERE language_name = ?");
        getStatementYear.setString(1, language);
        ResultSet languageResult = getStatementYear.executeQuery();

        if (languageResult.next()) {
            PreparedStatement preparedStatementLanguageCountry = connection.prepareStatement("INSERT INTO country_language" +
                    " (country_id, language_id) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatementLanguageCountry.setInt(1, generatedKeyCountry);
            preparedStatementLanguageCountry.setInt(2, languageResult.getInt(1));
            preparedStatementLanguageCountry.executeUpdate();

        } else {
            PreparedStatement preparedStatementLanguage = connection.prepareStatement("INSERT INTO language (language_name)" +
                    " VALUES(?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatementLanguage.setString(1, country.getLanguage());
            preparedStatementLanguage.executeUpdate();

            PreparedStatement preparedStatementLanguageCountry = connection.prepareStatement("INSERT INTO country_language" +
                    " (country_id, language_id) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);

            ResultSet generatedKeysLanguage = preparedStatementLanguage.getGeneratedKeys();
            generatedKeysLanguage.next();
            int generatedKeyLanguage = generatedKeysLanguage.getInt(1);

            preparedStatementLanguageCountry.setInt(1, generatedKeyCountry);
            preparedStatementLanguageCountry.setInt(2, generatedKeyLanguage);
            preparedStatementLanguageCountry.executeUpdate();
        }
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
