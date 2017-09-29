package com.harry.romanparser;


public class Main {

    // TODO: Better flow management
    public static void main(String[] args) {
        try {
            //split logic of reading json from logic of formatting
            RomanNumberFormatter romanNumberFormatter = new RomanNumberFormatter();
            UserInput userInput = new UserInput();

            String input = userInput.presentInputWithInstructions("Enter a number: ");
            Integer inputtedNumber = userInput.readAndVerifyUserInput(input);
            RomanNumber romanNumber = new RomanNumber(inputtedNumber);

            String formattedRomanNumber = romanNumberFormatter.format(romanNumber);

            System.out.println("Calculating Roman Numeral");
            System.out.println("Roman Numeral Equivalent: " + formattedRomanNumber);
            System.out.println(romanNumber.toString());

            captureTime();
            input = userInput.presentInputWithInstructions("Try another?: Y/N ");
            userInput.checkUserYesNoAnswer(input);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Class
    private static long timeCapture = 0L;

    public static long captureTime() {
        timeCapture = System.currentTimeMillis() - timeCapture;
        System.out.println("Time to calculate: " + timeCapture + " milliseconds");
        return timeCapture;
    }

}



