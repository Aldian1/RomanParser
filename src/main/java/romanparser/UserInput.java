package main.java.romanparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//scannning input and printing out...
public class UserInput {

    //printing and scanning
    public String presentInputWithInstructions(String instructions) {
        System.out.println(instructions);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
            input = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
    //Dont return param with int when you can break it into two
    //split this function
    public Integer readAndVerifyUserInput(String input) {
        try {
            Integer inputtedNumber = Integer.parseInt(input);
            if (input.length() < 1 || inputtedNumber >= 6000) {
                System.out.println("Invalid Number");
                checkUserYesNoAnswer(presentInputWithInstructions("Try another?: Y/N "));
            }
            return inputtedNumber;
        } catch (NumberFormatException e) {
            System.out.println("Invalid Number");
            checkUserYesNoAnswer(presentInputWithInstructions("Try another?: Y/N "));
        } catch (NullPointerException e) {
            System.out.println("Invalid Number");
            checkUserYesNoAnswer(presentInputWithInstructions("Try another?: Y/N "));
        }
        return 0;
    }

    public void checkUserYesNoAnswer(String input) {
        if ("y".equals(input) || "Y".equals(input)) {
            Main.main(null);
        }

        if ("n".equals(input) || "N".equals(input)) {
            System.exit(0);
        }
        return;
    }

}
