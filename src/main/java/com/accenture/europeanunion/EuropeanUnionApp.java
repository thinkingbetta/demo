package com.accenture.europeanunion;

import com.accenture.europeanunion.commands.*;
import com.accenture.europeanunion.entities.Country;
import com.accenture.europeanunion.entities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class EuropeanUnionApp {

        public Connection getConnection() throws SQLException {

            Connection conn = null;
            Properties connectionProps = new Properties();
            connectionProps.put("user", "root"); // Workbench credentials
            connectionProps.put("password", "secret");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/salsasyntax", connectionProps);
            System.out.println("Connected to database");
            return conn;
        }

    public void starter() throws SQLException {

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from record");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String bandName = resultSet.getString("band_name");
            String albumName = resultSet.getString("album_name");
            System.out.println("id" + id + " band " + bandName + " album " + albumName);
        }


        Scanner scanner = new Scanner(System.in);
        ArrayList<Country> countries = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();

        Command addCountryCommand = new AddCountryCommand(scanner, countries);
        Command exitCommand = new ExitCommand(users);
        Command unknownCommand = new UnknownCommand();
        Command addUserCommand = new AddUserCommand(scanner, users);
        Command howAreYouCommand= new HowAreYouCommand(scanner);
        Command countriesDescriptionCommand = new CountriesDescriptionCommand(countries);

        ArrayList<Command> commands = new ArrayList<>();
        commands.add(addCountryCommand);
        commands.add(countriesDescriptionCommand);
        commands.add(exitCommand);

        System.out.println("Welcome to European Union App!\nWhat's your name?");
        addUserCommand.run();
        howAreYouCommand.run();

        boolean suspend = false;
        while(!suspend){
            System.out.println("\nWhat do you want to do?");
            for (Command c : commands) {
                System.out.println(c.getCommandName());
            }
            String input = scanner.nextLine();

            boolean commandFound = false;
            for (Command command : commands) {
                if(command.getCommandName().equals(input)){
                    commandFound = true;
                    suspend = command.run();
                }
            }

            if(!commandFound){
                unknownCommand.run();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        new EuropeanUnionApp().starter();
    }





}
