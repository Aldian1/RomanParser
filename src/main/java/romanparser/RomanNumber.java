package main.java.romanparser;

public class RomanNumber {

    private int units;
    private int tens;
    private int hundreds;
    private int thousands;

    public RomanNumber(Integer number) {
        //takes inputted number and returns and OBJ
        int units = 0;
        int tens = 0;
        int hundreds = 0;
        int thousands = 0;

        if (number <= 9) {
            units = number;
        }
        if ((number >= 10) & number < 100) {
            tens = ((number - hundreds) / 10) * 10;
            units = (number - hundreds) - tens;
        }
        if ((number >= 100) & number < 1000) {
            hundreds = (number / 100) * 100;
            tens = ((number - hundreds) / 10) * 10;
            units = (number - hundreds) - tens;
        }
        if (number >= 1000) {
            thousands = (number / 1000) * 1000;
            hundreds = ((number - thousands) / 100) * 100;
            tens = ((number - thousands - hundreds) / 10) * 10;
            units = ((number - thousands) - hundreds) - tens;
        }
    }

    public int getUnits() {
        return units;
    }

    public int getTens() {
        return tens;
    }

    public int getHundreds() {
        return hundreds;
    }

    public int getThousands() {
        return thousands;
    }

    public String getNumber() {
        //return formattedVersion;
        return null;
    }

    //ovverides the .ToString method
    @Override
    public String toString() {
        return "RomanNumber: " + units + " ";
    }
}

