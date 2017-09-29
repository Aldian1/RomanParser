package com.harry.romanparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput {



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
