import java.util.*;
// this is the main class
public class StringManipulator {
    /*
        what goes into main method may include:
        (1) logics that deal with user interaction
        (2) utlization of the required methods
        (3) error checking of user input
    */
    public static void main(String[] args) {
        String pR = "Print Reverse";
        String rA = "Replace All";
        String rS = "Replace Single";
        String r = "Remove";
        String q = "Quit";
        String userInput = new String();
        Scanner sc = new Scanner(System.in);        
        while(true) { //allows only one string to be used the whole time
        System.out.println("Enter a string");
        userInput = new String(sc.nextLine());  
        validateString(userInput); //checks user input     
        while(true) { //forces user to manually quit
        System.out.println("Pick a command\r\n1. " + pR + "\r\n2. " + rA + "\r\n3. "+ rS + "\r\n4. " + r + "\r\n5. " + q);
        String command = new String(sc.nextLine());
        validateString(command); //checks user input
        if (command.equalsIgnoreCase(pR) || command.equals("1")) //checks user input case insensitive and if the number is chosen
            printReverse(userInput);
        else if (command.equalsIgnoreCase(rA) || command.equals("2")){
            System.out.println("Enter a charater to change");
            String strChar = new String(sc.nextLine());
            char oldChar = strChar.charAt(0);
            if (validateStringContainsChar(userInput, oldChar) == true) { //checks user input
               System.out.println("Enter new character");
               strChar = sc.nextLine();
               char newChar = strChar.charAt(0);
               userInput = replaceAll(userInput, oldChar, newChar);
            }
            else 
               System.out.println("Error! No instances occured");
        }
        else if (command.equalsIgnoreCase(rS) || command.equals("3")){
            System.out.println("Enter a charater to change");
            String strChar = new String(sc.nextLine());
            char oldChar = strChar.charAt(0);
            System.out.println("Enter new character");
            strChar = sc.nextLine();
            char newChar = strChar.charAt(0);
            System.out.println("Enter the instance you would like to replace");
            strChar = sc.nextLine();
            int userInstance = Integer.parseInt(strChar);
            int instance = validateOccurance(userInput, oldChar, userInstance); //checks user input with the original char and the user instance 
            if (instance > -1) //if user input is true
               userInput = replaceSingle(userInput, instance, newChar);//takes in the slot in the array that the char occurs in and the new char to change
            else if (instance == -1) //if user input is false
               System.out.println("Error! No instances occured");
        }
        else if (command.equalsIgnoreCase(r) || command.equals("4")){
            System.out.println("Enter a charater to remove");
            String strChar = new String(sc.nextLine());
            char c = strChar.charAt(0);
            if (validateStringContainsChar(userInput, c) == true) //checks user input
               userInput = remove(userInput, c);
            else
               System.out.println("Error! No instances occured");
        }
        else if (command.equalsIgnoreCase(q) || command.equals("5")) 
            break;
        }
        break;
        }
    }

    // this method checks if any given input string is empty
    // if empty, alert users who goes wrong and return false
    // otherwise return true
    /* e.g.1, str: godawgo
             char: ""
             print: Error - There is no input
             return: false
       e.g.2, str: ""
             char: s
             print: Error - There is no input
             return: false
    */
    public static boolean validateString(String str) {
        if (str == null || str.length() == 0) {
               System.out.println("Error! No input given");
               return false;
            }
        else
         return true;
    }

    // this method checks if the given character c is contained by the string str
    // if c is not contained by str, alert users what goes wrong and return false
    // otherwise return true
    /* e.g.1, str: godawgo
             char: o
             return: true
       e.g.2, str: godawgo
             char: s
             print: Error - The string does not contain the given character
             return: false
    */
    public static boolean validateStringContainsChar(String str, char c) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == c) //checks current character in the string to the users input
               return true;
        }
        System.out.println("Error! " + c + " is not in " + str);
        return false;
    }

    // this method takes a string and print it reversely
    public static void printReverse(String str) {
        String reverse = "";
        for (int i = str.length()-1; i >= 0; i--) { //prints from the last spot in the array to 0 place
            reverse += str.charAt(i); //added into the array left to right
        }
        System.out.println(reverse);
        System.out.println();
    }

    // this method replace oldChar with newChar for any occurance of oldChar
    // you should assume the validity of all parameters
    /* e.g.1, str: godawgo
             oldChar: o
             newChar: t
             return: gtdawgt
    */
    public static String replaceAll(String str, char oldChar, char newChar) {
        str = str.replace(oldChar, newChar); //used the replace method in the string class
        System.out.println(str);
        System.out.println();
        return str;
    }

    // this method checks whether the give occurance of c in str is valid
    // you should assume that c is contained by str - this can be guaranteed by validateChar
    // if c does not have an number (e.g., 3rd) of occurance, return -1
    // otherwise, return the index of c at the number (e.g., 3rd) of occurance
    /* e.g.1, str: godawgo
             char: o
             number: 2
             return: 6
       e.g.2, str: godawgo
             char: o
             number: 3
             return: -1
    */
    
    //this was really difficult to figure out itself and how it fit with replaceSingle method with the given parameters
    public static int validateOccurance(String str, char c, int number) { 
        int count = 0; //
        for (int i = 0; i < str.length(); i++) {
            char s = str.charAt(i);
            if (s == c){ //checks if the current char is the same as the inputed char
               count++; //adds to counter
            }
            if (count == number) { //checks if the counter equals the inputed instance
               return i; //returns the current spot in the string
            }  
        }   
        return -1; //if everything fails then returns -1 
    }

    // this method replace the character at index i with newChar
    // this method may take advantage of the validateOccurance method 
    // all parameters should be assumed valid
    /* e.g.1, str: godawgo
             i: 2
             newChar: s
             return: gosawgo
    */
    public static String replaceSingle(String str, int i, char newChar) {
        str = str.substring(0, i) + newChar + str.substring(i + 1); //prints part of the array adds the new char then prints the rest
        System.out.println(str);
        return str;
    }

    // this method removes character c at any occurance
    // all parameters should be assumed valid
    /* e.g.1, str: godawgo
             char: o
             return: gdawg
    */
    public static String remove(String str, char c) {
        String ch = Character.toString(c);
        str = str.replaceAll(ch, ""); //uses string method replace all to remove the char
        System.out.println(str);
        System.out.println();
        return str;
    }

    /*
        You are welcome to add more methods below
    */

}
