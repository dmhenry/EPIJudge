package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PhoneNumberMnemonic {

    private static final String[] KEY_MAPPINGS = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};


    @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
    public static List<String> phoneMnemonic(String phoneNumber) {
        List<String> results = new ArrayList<>();
        addMnemonicRecur(phoneNumber, 0, "", results);
        return results;
    }

    private static void addMnemonicRecur(String phoneNumber, int index, String mnemonicPrefix, List<String> results) {
        int key = phoneNumber.charAt(index) - '0';
        char letter;

        String keyLetters = KEY_MAPPINGS[key];

        for (int i = 0; i < keyLetters.length(); i++) {
            letter = keyLetters.charAt(i);
            if (index == phoneNumber.length() - 1) {
                results.add(mnemonicPrefix + letter);
            } else {
                addMnemonicRecur(phoneNumber, index + 1, mnemonicPrefix + letter, results);
            }
        }
    }

    @EpiTestComparator
    public static boolean comp(List<String> expected, List<String> result) {
        if (result == null) {
            return false;
        }
        Collections.sort(expected);
        Collections.sort(result);
        return expected.equals(result);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PhoneNumberMnemonic.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
