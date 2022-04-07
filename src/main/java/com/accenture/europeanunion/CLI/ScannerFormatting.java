package com.accenture.europeanunion.CLI;

import java.util.Scanner;

public class ScannerFormatting {

    public String getFormattedString(Scanner scanner) {

        String string = scanner.nextLine().trim();

        String firstLetter = string.substring(0, 1).toUpperCase();
        String restOfTheWord = string.substring(1).toLowerCase();

        return firstLetter+restOfTheWord;
    }

    //TODO togliere ogni carattere speciale da numero inserito
    public int getFormattedInt(Scanner scanner) {
        return Integer.parseInt(scanner.nextLine().trim());
    }
}
