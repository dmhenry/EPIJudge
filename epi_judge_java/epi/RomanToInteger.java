package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RomanToInteger {

    @EpiTest(testDataFile = "roman_to_integer.tsv")
    public static int romanToInteger(String s) {
        int value = 0;
        RomanNumeral curr = RomanNumeral.of(s.charAt(0)), next;

        for (int i = 1; i < s.length(); i++) {
            next = RomanNumeral.of(s.charAt(i));
            if (curr.compareTo(next) < 0) {
                value -= curr.value;
            } else {
                value += curr.value;
            }
            curr = next;
        }
        value += curr.value;
        return value;
    }

    private enum RomanNumeral {

        I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

        RomanNumeral(int value) {
            this.value = value;
        }

        int value;

        private static RomanNumeral of(char numeral) {
            switch (numeral) {
                case 'I': return I;
                case 'V': return V;
                case 'X': return X;
                case 'L': return L;
                case 'C': return C;
                case 'D': return D;
                case 'M': return M;
            }
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RomanToInteger.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
