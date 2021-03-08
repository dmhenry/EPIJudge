package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class StringIntegerInterconversion {

    public static String intToString(int x) {
        if (x == 0)
            return "0";

        StringBuilder result = new StringBuilder();
        long num = x;
        if (num < 0) {
            num *= -1;
        }

        while (num > 0) {
            result.append(num % 10);
            num /= 10;
        }

        if (x < 0) {
            result.append("-");
        }
        return result.reverse().toString();
    }

    public static int stringToInt(String s) {
        int sign = 1;
        long digits = 0;    // Avoid overflow when s = "-2147483648"

        if (s.charAt(0) == '-') {
            sign = -1;
        } else if (s.charAt(0) != '+') {
            digits = s.charAt(0) - '0';
        }

        for (int i = 1; i < s.length(); i++) {
            digits = digits * 10 + (s.charAt(i) - '0');
        }
        return (int) (sign * digits);
    }

    @EpiTest(testDataFile = "string_integer_interconversion.tsv")
    public static void wrapper(int x, String s) throws TestFailure {
        if (Integer.parseInt(intToString(x)) != x) {
            throw new TestFailure("Int to string conversion failed");
        }
        if (stringToInt(s) != x) {
            throw new TestFailure("String to int conversion failed");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
