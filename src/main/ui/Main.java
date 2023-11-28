package ui;

import java.io.FileNotFoundException;

/*
 * Starts the FinanciallyFit application by running FinanciallyFitConsoleUI
 */

// PHASE 2 COMMENT: save/load functionality (persistence) has been implemented based off of JsonSerializationDemo.
// PHASE 3 COMMENT: execution of Console UI has been commented out; can still be used by commenting GUI out and
//                  uncommenting GUI. the java website for BoxLayoutDemo and SplitPaneDemo was consulted.

public class Main {
    public static void main(String[] args) {
        try {
           // new FinanciallyFitConsoleUI();  //swap commented out code to choose between console ui and gui.
            new FinanciallyFitGUI();  // <-------|
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted while running");
        }
    }
}