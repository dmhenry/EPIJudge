package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneNumberMnemonic {

    private static final Map<Character, List<Character>> charsByNum = new HashMap<>();

    static {
        charsByNum.put('0', Collections.singletonList('0'));
        charsByNum.put('1', Collections.singletonList('1'));
        charsByNum.put('2', Arrays.asList('A', 'B', 'C'));
        charsByNum.put('3', Arrays.asList('D', 'E', 'F'));
        charsByNum.put('4', Arrays.asList('G', 'H', 'I'));
        charsByNum.put('5', Arrays.asList('J', 'K', 'L'));
        charsByNum.put('6', Arrays.asList('M', 'N', 'O'));
        charsByNum.put('7', Arrays.asList('P', 'Q', 'R', 'S'));
        charsByNum.put('8', Arrays.asList('T', 'U', 'V'));
        charsByNum.put('9', Arrays.asList('W', 'X', 'Y', 'Z'));
    }

    @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
    public static List<String> phoneMnemonic(String phoneNumber) {
        List<String> results = new ArrayList<>();
        addMnemonicRecur(phoneNumber, 0, "", results);
        return results;
    }

    private static void addMnemonicRecur(String phoneNumber, int i, String mnemonicPrefix, List<String> results) {
        char digit = phoneNumber.charAt(i);
        List<Character> keyLetters = charsByNum.get(digit);

        for (Character letter : keyLetters) {
            if (i == phoneNumber.length() - 1) {
                results.add(mnemonicPrefix + letter);
            } else {
                addMnemonicRecur(phoneNumber, i + 1, mnemonicPrefix + letter, results);
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
