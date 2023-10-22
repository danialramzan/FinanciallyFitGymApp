package ui;

import java.io.FileNotFoundException;

/*
 * Starts the FinanciallyFit application by running FinanciallyFitUI
 */
public class Main {
    public static void main(String[] args) {
        try {
            new FinanciallyFitUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}