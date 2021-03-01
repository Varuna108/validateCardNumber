package tests.validate.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelperMethods {
    private static final List<String> listOfValidCard = new ArrayList<String>();

    public static String moonAlgorithm(String value) {
            int sum1 = 0;
            int sum2 = 0;
            final int nDigits = value.length();
            for (int i = nDigits; i > 0; i--) {
                int digit = Character.getNumericValue(value.charAt(i - 1));
                int z = digit;
                int y = digit;
                if (i % 2 != 0) {
                    z *= 2;
                    if (z > 9) {
                        z -= 9;
                    }
                    sum1 += z;
                } else sum2 += y;
            }
            int sum = sum1 + sum2;
            if (value.length() != 16) sum = 1;
            if (sum % 10 == 0) {
                listOfValidCard.add(value.substring(0, 8));
                return ("Card Valid" + value);
            } else {
                return ("Card not Valid" + value);
            }
    }


    public static String[] stringToArray(String value) {
        String newStr  = value.replaceAll("(.{16})", "$1|");
        String[] newStrings = newStr.split("\\|");
        System.out.println("List of all cards: " + Arrays.toString(newStrings));
        return newStrings;
        }

    public static String[] arrayValidCard() {
        return listOfValidCard.toArray((new String[listOfValidCard.size()]));
    }
}