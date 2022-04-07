package com.accenture.europeanunion;

import com.accenture.europeanunion.CLI.ScannerFormatting;
import com.accenture.europeanunion.commands.*;
import com.accenture.europeanunion.entities.Country;
import com.accenture.europeanunion.entities.User;
import com.accenture.europeanunion.perseverance.DatabaseConnector;

import java.sql.*;//connection, could also be written in POM
import java.util.ArrayList;
import java.util.Scanner;

public class EuropeanUnionApp {
    public void starter() throws SQLException {

        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.getConnection();

//        Connection connection = getConnection();

        Scanner scanner = new Scanner(System.in);
        ScannerFormatting scannerFormatting = new ScannerFormatting();

        ArrayList<Country> countries = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();


        Command addCountryCommand = new AddCountryCommand(scanner, connection, scannerFormatting);
        Command exitCommand = new ExitCommand(users);
        Command unknownCommand = new UnknownCommand();
        Command addUserCommand = new AddUserCommand(scanner, users);
        Command howAreYouCommand= new HowAreYouCommand(scanner);
        Command countriesDescriptionCommand = new CountriesDescriptionCommand(connection);

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
            String input = scanner.nextLine().trim();

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
