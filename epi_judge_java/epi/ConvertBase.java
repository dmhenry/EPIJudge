package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ConvertBase {
    @EpiTest(testDataFile = "convert_base.tsv")

    public static String convertBase(String numAsString, int b1, int b2) {
        StringBuilder result = new StringBuilder();
        long decimal = toDecimal(numAsString, b1);
        boolean isNegative = false;

        if (decimal == 0)
            return "0";

        if (decimal < 0) {
            decimal *= -1;
            isNegative = true;
        }

        while (decimal > 0) {
            long d = decimal % b2;
            if (d < 10) {
                result.append(d);
            } else {
                result.append((char) ('A' + d - 10));
            }
            decimal /= b2;
        }

        if (isNegative) {
            result.append('-');
        }
        return result.reverse().toString();
    }

    private static long toDecimal(String numAsString, int radix) {
        long decimal = 0;
        int sign = (numAsString.startsWith("-")) ? -1 : 1;
        int digit;
        int ch;

        for (int i = (sign == -1) ? 1 : 0; i < numAsString.length(); i++) {
            ch = numAsString.charAt(i);
            digit = Character.isDigit(ch) ? ch - '0' : Character.toUpperCase(ch) - 'A' + 10;
            decimal = decimal * radix + digit;
        }
        return sign * decimal;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
