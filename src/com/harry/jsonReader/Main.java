package com.harry.jsonReader;

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
	
	public static void main(String[] args)
	{
			CalculateRomanNumeral(UserInput());
			main(null);     
	}
	
	public static void CalculateRomanNumeral(String input)
	{
		
		try { 
	        Integer.parseInt(input); 
	    } catch(NumberFormatException e) { 
	    	System.out.println("Please enter a valid number");
	        return; 
	    } catch(NullPointerException e) {
	    	System.out.println("Please enter a valid number");
	        return;
	    }
		
			
		
		//TODO: How to error check against an empty input 
		if(input.length() < 1)
		{
		System.out.println("Please input a number larger than 0");
		return;
		}else if(Integer.parseInt(input) > 6000)
		{
			System.out.println("No known conversion for numbers greater than 6000");
			return;
		}
		JSONObject romanNumeralRef = GetJsonObject("assets/reference.json");
		JSONArray romanNumeralUnits = (JSONArray) romanNumeralRef.get("Units");
		JSONArray romanNumeralTens = (JSONArray) romanNumeralRef.get("Tens");
		JSONArray romanNumeralHundreds = (JSONArray) romanNumeralRef.get("Hundreds");
		JSONArray romanNumeralThousands = (JSONArray) romanNumeralRef.get("Thousands");
		
		
		Integer inputtedNumber = Integer.parseInt(input);
		int units = 0;
		int tens = 0;
		int hundreds = 0;
		int thousands = 0; 
		
		
		if(inputtedNumber < 10)
		{
			units = inputtedNumber;
		}
		if(inputtedNumber >= 10 & inputtedNumber < 100)
		{
			tens = ((inputtedNumber - hundreds) / 10) * 10; 
			units = (inputtedNumber - hundreds) - (tens);
		}
		
		if(inputtedNumber >= 100 & inputtedNumber < 1000)
		{
			
			hundreds = (inputtedNumber / 100) * 100;
			tens = ((inputtedNumber - hundreds) / 10) * 10; 
			units = (inputtedNumber - hundreds) - (tens);	
		}	
		if(inputtedNumber >= 1000)
		{
			thousands = (inputtedNumber / 1000) * 1000;
			hundreds = ((inputtedNumber - thousands) / 100) * 100;
			tens = ((inputtedNumber - thousands - hundreds) / 10) * 10; 
			units = ((inputtedNumber - thousands) - hundreds) - tens;
		}	
		
		String unitsResult = SearchJsonArray(units, romanNumeralUnits);
		String tensResult = SearchJsonArray(tens, romanNumeralTens);
		String hundredsResult = SearchJsonArray(hundreds, romanNumeralHundreds);
		String thousandsResult = SearchJsonArray(thousands, romanNumeralThousands);
		System.out.println("Roman Numeral Equivalent: " + thousandsResult + hundredsResult + tensResult + unitsResult);	
		System.out.println("Units: " + units);
		System.out.println("Tens: " + tens);
		System.out.println("Hundreds: " + hundreds);
		System.out.println("Thousands: " + thousands);

	}
	
	
	public static String UserInput()
	{
		System.out.println("Enter a number: ");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		try {
			input = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return input;
	}
	
	public static String SearchJsonArray(Integer key, JSONArray arrayToSearch)
	{
		
	    for (int i = 0; i < arrayToSearch.size(); i++)
	    {
	         JSONObject item = (JSONObject) arrayToSearch.get(i);
	        	 Integer convertedResult = Integer.parseInt((String) item.get("key"));
	        	 if(key.equals(convertedResult)) 
	        	 {
	        		 return (String) item.get("val");
	        	 }
	    }
	    
	    return "====== Cannot find key [" + key + "] in "+ arrayToSearch +" ======  ";
	}
	
	public static JSONObject GetJsonObject(String path)
	{
		JSONParser parser = new JSONParser();
		try {
		Object obj = (JSONObject) parser.parse(new FileReader(path));
		JSONObject jsonObject = (JSONObject) obj;
		//System.out.println(jsonObject);
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
