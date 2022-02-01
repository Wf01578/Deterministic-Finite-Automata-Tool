/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theoryprojectrev2;

/**
 *
 * @author will
 */
import java.util.Scanner;
import java.io.*;

public class DFA {

    // Fields
    protected int currentState = 0;     //keeps track of current state during string processing
    protected int numStates;            // the number of states in the DFA
    protected String alphabet;
    protected int start;                // start state
    protected State[] stateArray;       // collection of states...

    // Methods
    // displayAlphabet() - displays the DFA's alphabet
    // Input: none        Output: none
    public void display() {
        System.out.println("\nDFA\'s Alphabet = " + alphabet);
    }

    // processInput() - Wrapper - processes the input string, resets currentState
    //                  to start state,  returns true for accept
    // Input: String        Output: none
    public boolean processInput(String inputString) {

        if (inputString == null) // if char is null - check if state 0 is accept
        {
            return stateArray[start].isAcceptState();
        }

        boolean accept;

        accept = process(inputString);
        reset();
        return accept;
    }

    // process() - processes the input string, returns true for accept
    // Input: String        Output: none
    protected boolean process(String inputString) {

        int aChar;

        for (int i = 0; i < inputString.length(); ++i) {
            aChar = inputString.charAt(i);  // get char
            int charIndex = alphabet.indexOf(aChar);

            if (charIndex == -1) {  // else check if in alphabet
                return false;
            } else {
                currentState = stateArray[currentState].getTransition(charIndex);
            }
        }

        return stateArray[currentState].isAcceptState();
    }

    public int loadFile(String fileName) {

        InputStream in;
        in = getFileInputStream(fileName);

        if (in == null) {
            System.out.println("File not found.");
            return 0;
        }
        //get the alphabet
        Scanner sline = new Scanner(in).useDelimiter("\n");     // load in each line as a string with this delimited scanner
        String prealpha = sline.next();
        char place;
        alphabet = "";
        for (int i = 0; i < prealpha.length(); i++) {
            place = prealpha.charAt(i);
            if (Character.isLetterOrDigit(place)) {           // each single alphanumeric character is seperated from the brackets and commas
                alphabet = alphabet + place;                // and stored in the alphabet string, each charachter representing a member of the alphabet
            } else {
                continue;
            }
        }
        //alphabet loaded

        //load states
        String preStates = sline.next();        // read in the next line
        int stateCounter = 0;
        String stateString = "";
        stateArray = new State[8];                          // set max num states
        for (int i = 0; i < preStates.length(); i++) {
            place = preStates.charAt(i);
            if (Character.isLetter(place)) {                                      // for each character in the state line, create a new State object in the state Array
                stateArray[stateCounter] = new State(alphabet.length());
                stateCounter++;
                stateString = stateString + place;
            } else {
                continue;
            }
        }
        numStates = stateCounter;                       // the actual number of states read from the line

        String preStartState = sline.next();
        char startTemp = preStartState.charAt(0);
        start = stateString.indexOf(startTemp);
        currentState = start;                   // setting the current State to the start state

        String rawAcceptStates = sline.next();
        for (int i = 0; i < rawAcceptStates.length(); i++) {
            place = rawAcceptStates.charAt(i);
            if (Character.isLetter(place)) {
                stateArray[stateString.indexOf(place)].setAcceptState();    // set the states in the array as accept states
            }
        }
        int aStateLocation = -1;
        int trans1 = -1;
        int trans2 = -1;

        String transitionString;
        String processedTransitionString;
        while (sline.hasNext()) {
            transitionString = sline.next();
            processedTransitionString = "";
            for (int i = 0; i < transitionString.length(); i++) {
                place = transitionString.charAt(i);
                if (Character.isLetterOrDigit(place)) {
                    processedTransitionString = processedTransitionString + place;
                }
            }
            aStateLocation = stateString.indexOf(processedTransitionString.charAt(0));
            if (processedTransitionString.charAt(1) == '0') {
                trans1 = stateString.indexOf(processedTransitionString.charAt(2));
                stateArray[aStateLocation].setTransitions(0, trans1);
            } else {
                trans2 = stateString.indexOf(processedTransitionString.charAt(2));
                stateArray[aStateLocation].setTransitions(1, trans2);
            }
        }

        return 1;
    }

    // getFileInputStream(): Returns an input stream that gets data from a named file
    // Input: String        Output: none
    protected static InputStream getFileInputStream(String fileName) {

        InputStream inputStream;

        try {
            inputStream = new FileInputStream(new File(fileName));
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            inputStream = null;
        }

        return inputStream;
    }

    // reset(): sets the currentState back to the start
    // Input: none        Output: none
    public void reset() {
        currentState = start;
    }

}
