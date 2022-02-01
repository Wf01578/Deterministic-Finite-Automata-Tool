/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theoryprojectrev2;

import java.util.Scanner;
import java.io.*;

public class TheoryProjectRev2 {

    public static void main(String[] args) {

        Scanner input = null;       //create a Scanner object
        input = new Scanner(System.in);

        System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.println("~~ Deterministic Finite Automaton (DFA)~~");

        publicTest(input);     // user provides fileName
        //preFabTest(input);      // a run through all test files...

        input.close();
        System.out.println("\nGood Bye... ");
    }

    // publicTest() - user interface allowing user to enter a dfa txt file
    // Input: Scanner        Output: none
    protected static void publicTest(Scanner input) {
        DFA myDFA = new DFA();      // The DFA

        String fileName;            // File for a DFA
        System.out.print("Please enter the file name: ");
        fileName = input.nextLine();

        if (myDFA.loadFile(fileName) == 0) {
            System.out.print("File does not exist");

        } else {
            myDFA.display();

            String inputString = getString(input);  // get test string
            printResults(myDFA.processInput(inputString));

            inputString = getString(input);  // get test string
            printResults(myDFA.processInput(inputString));

            inputString = getString(input);  // get test string
            printResults(myDFA.processInput(inputString));

        }

    }

    // testing() - another helper function to help me test dfas
    // Input: DFA, String, Scanner        Output: none
    protected static void testing(DFA aDFA, String aFileName, Scanner input) {

        aDFA.loadFile(aFileName);
        System.out.println("*******************************************\n");
        System.out.println("\033[1mFile: \"" + aFileName + "\" \033[0m \n");
        aDFA.display();

        String inputString = getString(input);      // get test string
        printResults(aDFA.processInput(inputString));

        String inputString_2 = getString(input);      // get test string
        printResults(aDFA.processInput(inputString_2));

        String inputString_3 = getString(input);      // get test string
        printResults(aDFA.processInput(inputString_3));

    }

    // printResults() - prints accept or
    // Input: none        Output: none
    protected static void printResults(boolean result) {
        if (result) {
            System.out.println("\n\tACCEPT ");
        } else {
            System.out.println("\n\tREJECT ");
        }
    }

    // getString() - gets user input for string
    // Input: Scanner        Output: none
    protected static String getString(Scanner input) {
        System.out.print("\nPlease enter a string to test: ");
        return input.nextLine();
    }
}
