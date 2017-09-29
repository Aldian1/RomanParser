package com.harry.romanparser;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class Main {


    private Results results;
    private JSONFileReferencer jsonFileReferencer;
    private Printer printer;
    private Extractor extractor;
    private UserInput userInput;
    private long timeCapture = 0L;


    // TODO: Better flow management
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        try {
            results = new Results();
            jsonFileReferencer = new JSONFileReferencer();
            printer = new Printer();
            extractor = new Extractor();
            userInput = new UserInput();

            
            calculateRomanNumeral(userInput.presentInputWithInstructions("Enter a number: "));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void calculateRomanNumeral(String input) {
        Integer inputtedNumber = userInput.readAndVerifyUserInput(input);
        extractor.extractNumber(inputtedNumber, results);
        printer.runPrinter(jsonFileReferencer.getRomanNumeral(results), results);
        captureTime();
        userInput.checkUserYesNoAnswer(userInput.presentInputWithInstructions("Try another?: Y/N "));
    }

    public long captureTime() {
        timeCapture = System.currentTimeMillis() - timeCapture;
        System.out.println("Time to calculate: " + timeCapture + " milliseconds");
        return timeCapture;
    }

}



