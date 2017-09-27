package com.harry.romanparser;
import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

	// TODO: Better flow management
	public static void main(String[] args) {
		try {
			calculateRomanNumeral(userInput("Enter a number: "));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void calculateRomanNumeral(String input) {
		Integer inputtedNumber = readAndVerifyUserInput(input);
		extractNumericalUnits(inputtedNumber);
	}

	private static void extractNumericalUnits(int inputtedNumber) {
		int units = 0;
		int tens = 0;
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
		printResults(units, tens, hundreds, thousands);
	}

	private static void printResults(Integer units, Integer tens, Integer hundreds, Integer thousands) {
		JSONObject romanNumeralRef = getJsonObject("assets/reference.json");
		JSONArray romanNumeralUnits = (JSONArray) romanNumeralRef.get("Units");
		JSONArray romanNumeralTens = (JSONArray) romanNumeralRef.get("Tens");
		JSONArray romanNumeralHundreds = (JSONArray) romanNumeralRef.get("Hundreds");
		JSONArray romanNumeralThousands = (JSONArray) romanNumeralRef.get("Thousands");
		String unitsResult = searchJSONArray(units, romanNumeralUnits);
		String tensResult = searchJSONArray(tens, romanNumeralTens);
		String hundredsResult = searchJSONArray(hundreds, romanNumeralHundreds);
		String thousandsResult = searchJSONArray(thousands, romanNumeralThousands);
		System.out.println("Roman Numeral Equivalent: " + thousandsResult + hundredsResult + tensResult + unitsResult);
		System.out.println("Units: " + units);
		System.out.println("Tens: " + tens);
		System.out.println("Hundreds: " + hundreds);
		System.out.println("Thousands: " + thousands);
		askUserToStartAgain(userInput("Try another?: Y/N "));
	}

	private static void askUserToStartAgain(String input) {
		if("y".equals(input) || "Y".equals(input))
		{
			main(null);
		}else {
			return;
		}
	}

	private static Integer readAndVerifyUserInput(String input) {
		try {
			Integer inputtedNumber = Integer.parseInt(input);
			if (input.length() < 1) {
				throw new RuntimeException("Please input a number larger than 0");
			} else if (inputtedNumber >= 6000) {
				askUserToStartAgain(userInput("Try another?: Y/N "));
				throw new RuntimeException("No known conversion for numbers greater than 6000");
			}
			return inputtedNumber;
		} catch (NumberFormatException e) {
			throw new RuntimeException("Please enter a valid number");
		} catch (NullPointerException e) {
			throw new RuntimeException("Please enter a valid number");

		}

	}

	public static String userInput(String instructions) {
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

	public static String searchJSONArray(Integer key, JSONArray arrayToSearch) {
		for (int i = 0; i < arrayToSearch.size(); i++) {
			JSONObject item = (JSONObject) arrayToSearch.get(i);
			Integer convertedResult = Integer.parseInt((String) item.get("key"));
			if (key.equals(convertedResult)) {
				return (String) item.get("val");
			}
		}
		return "====== Cannot find key [" + key + "] in " + arrayToSearch + " ======  ";
	}

	public static JSONObject getJsonObject(String path) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = (JSONObject) parser.parse(new FileReader(path));
			JSONObject jsonObject = (JSONObject) obj;
			// System.out.println(jsonObject);
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
}
