package com.harry.romanparser;

import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONFileReferencer {



    private String JSON_PATH = "assets/reference.json";
    public Results storedResults;


    public String getRomanNumeral(Results results)
    {
        storedResults = results;
        return getJSONReferencesAndConvert();
    }

    private String getJSONReferencesAndConvert() {

        JSONObject romanNumeralRef = getJsonObject(JSON_PATH);
        if(romanNumeralRef != null) {
            JSONArray romanNumeralUnits = (JSONArray) romanNumeralRef.get("Units");
            JSONArray romanNumeralTens = (JSONArray) romanNumeralRef.get("Tens");
            JSONArray romanNumeralHundreds = (JSONArray) romanNumeralRef.get("Hundreds");
            JSONArray romanNumeralThousands = (JSONArray) romanNumeralRef.get("Thousands");
            String unitsResult = searchJSONArray(storedResults.getUnits(), romanNumeralUnits);
            String tensResult = searchJSONArray(storedResults.getTens(), romanNumeralTens);
            String hundredsResult = searchJSONArray(storedResults.getHundreds(), romanNumeralHundreds);
            String thousandsResult = searchJSONArray(storedResults.getThousands(), romanNumeralThousands);
            return unitsResult + tensResult + hundredsResult + thousandsResult;
            //TODO: return the string as one cancenated one
        }
        return "Cannot find JSON file";
    }

    @Nullable
    private JSONObject getJsonObject(String path) {
        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
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



}
