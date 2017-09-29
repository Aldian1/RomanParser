package com.harry.romanparser;

public class Printer {


    private Main main;

    public void runPrinter(String romanNumber, Results results)
    {
        System.out.println("Calculating Roman Numeral");
        printNumericalResults(romanNumber, results);

    }


    private void printNumericalResults(String romanNumber, Results results) {

        System.out.println("Roman Numeral Equivalent: " + romanNumber );
        System.out.println("Units: " + results.getUnits());
        System.out.println("Tens: " + results.getTens());
        System.out.println("Hundreds: " + results.getHundreds());
        System.out.println("Thousands: " + results.getThousands());

    }
}
