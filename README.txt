Written by: William "Alex" Fletcher 

Program to models DFA's in java:
 
User enters a file location, from which a DFA's attributes are read from.
The DFA is instantiated as an object, with attributes such as an array of State objects, and an alphabet string to name a few.
Once the DFA is fully initialized the user is prompted for a string, with which the DFA will return whether the string is part of the accepted language or not. This is accomplished by checking each input character to make sure it is a member of the alphabet, and then checking the State object array for the corresponding states transition given the input. If the final character of the string is read and the current state of the DFA is an accept state, the program returns that the string is valid. The user can enter 3 strings to be tested on the same DFA per program run.