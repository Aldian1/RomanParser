package com.harry.romanparser;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



class UnitsObject {

    private int units=0;
    private int tens=0;
    private int hundreds=0;
    private int thousands=0;

         public UnitsObject(int a, int b, int c, int d)
         {
         units = a;
         tens = b;
         hundreds = c;
         thousands = d;
         }
}

public class Main {

    long timeCapture = 0L;

    // TODO: Better flow management
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        try {
            calculateRomanNumeral(userInput("Enter a number: "));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void calculateRomanNumeral(String input) {

        Integer inputtedNumber = readAndVerifyUserInput(input);
        extractNumericalUnits(inputtedNumber);
        getJSONReferencesAndConvert();
        printResults();
        checkUserYesNoAnwser(userInput("Try another?: Y/N "));
    }

    private void extractNumericalUnits(int inputtedNumber) {
        //takes inputted number and returns and OBJ
        int units = 0;
        int tens =0;
        int hundreds = 0;
        int thousands = 0;

        if (inputtedNumber <= 9) {
            units = inputtedNumber;
        }
        if ((inputtedNumber >= 10) & inputtedNumber < 100) {
            tens = ((inputtedNumber - hundreds) / 10) * 10;
            units = (inputtedNumber - hundreds) - (tens);
        }
        if ((inputtedNumber >= 100) & inputtedNumber < 1000) {
            hundreds = (inputtedNumber / 100) * 100;
            tens = ((inputtedNumber - hundreds) / 10) * 10;
            units = (inputtedNumber - hundreds) - (tens);
        }
        if (inputtedNumber >= 1000) {
            thousands = (inputtedNumber / 1000) * 1000;
            hundreds = ((inputtedNumber - thousands) / 100) * 100;
            tens = ((inputtedNumber - thousands - hundreds) / 10) * 10;
            units = ((inputtedNumber - thousands) - hundreds) - tens;
        }


    }

    private void getJSONReferencesAndConvert() {
        int units = 0;
        int tens = 0;
        int hundreds = 0;
        int thousands = 0;
        JSONObject romanNumeralRef = getJsonObject("assets/reference.json");
        JSONArray romanNumeralUnits = (JSONArray) romanNumeralRef.get("Units");
        JSONArray romanNumeralTens = (JSONArray) romanNumeralRef.get("Tens");
        JSONArray romanNumeralHundreds = (JSONArray) romanNumeralRef.get("Hundreds");
        JSONArray romanNumeralThousands = (JSONArray) romanNumeralRef.get("Thousands");
        String unitsResult = searchJSONArray(units, romanNumeralUnits);
        String tensResult = searchJSONArray(tens, romanNumeralTens);
        String hundredsResult = searchJSONArray(hundreds, romanNumeralHundreds);
        String thousandsResult = searchJSONArray(thousands, romanNumeralThousands);
        return;
    }

    private void printResults() {
        System.out.println("Roman Numeral Equivalent: " );//+ thousandsResult + hundredsResult + tensResult + unitsResult);
       /* System.out.println("Units: " + units);
        System.out.println("Tens: " + tens);
        System.out.println("Hundreds: " + hundreds);
        System.out.println("Thousands: " + thousands);*/
        System.out.println("Time to calculate: " + captureTime() + " milliseconds");
    }

    private void checkUserYesNoAnwser(String input) {
        if ("y".equals(input) || "Y".equals(input)) {
            main(null);
        }

        if ("n".equals(input) || "N".equals(input)) {
            System.exit(0);
        }
        return;

    }

    private Integer readAndVerifyUserInput(String input) {
        try {
            Integer inputtedNumber = Integer.parseInt(input);
            if (input.length() < 1 || inputtedNumber >= 6000) {
                System.out.println("Invalid Number");
                checkUserYesNoAnwser(userInput("Try another?: Y/N "));
            }
            return inputtedNumber;
        } catch (NumberFormatException e) {
            System.out.println("Invalid Number");
            checkUserYesNoAnwser(userInput("Try another?: Y/N "));
        } catch (NullPointerException e) {
            System.out.println("Invalid Number");
            checkUserYesNoAnwser(userInput("Try another?: Y/N "));
        }
        return 0;
    }

    private String userInput(String instructions) {
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

    private String searchJSONArray(Integer key, JSONArray arrayToSearch) {
        for (int i = 0; i < arrayToSearch.size(); i++) {
            JSONObject item = (JSONObject) arrayToSearch.get(i);
            Integer convertedResult = Integer.parseInt((String) item.get("key"));
            if (key.equals(convertedResult)) {
                return (String) item.get("val");
            }
        }
        return "====== Cannot find key [" + key + "] in " + arrayToSearch + " ======  ";
    }

    private JSONObject getJsonObject(String path) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = (JSONObject) parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private long captureTime() {
        timeCapture = System.currentTimeMillis() - timeCapture;
        return timeCapture;
    }
}
