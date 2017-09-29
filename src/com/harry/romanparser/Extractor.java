package com.harry.romanparser;

public class Extractor {

    public void extractNumber(int inputtedNumber, Results results) {
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
            units = (inputtedNumber - hundreds) - tens;
        }
        if ((inputtedNumber >= 100) & inputtedNumber < 1000) {
            hundreds = (inputtedNumber / 100) * 100;
            tens = ((inputtedNumber - hundreds) / 10) * 10;
            units = (inputtedNumber - hundreds) - tens;
        }
        if (inputtedNumber >= 1000) {
            thousands = (inputtedNumber / 1000) * 1000;
            hundreds = ((inputtedNumber - thousands) / 100) * 100;
            tens = ((inputtedNumber - thousands - hundreds) / 10) * 10;
            units = ((inputtedNumber - thousands) - hundreds) - tens;
        }

        results.setUnits(units);
        results.setTens(tens);
        results.setHundreds(hundreds);
        results.setThousands(thousands);

    }
}
