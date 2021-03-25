package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LookAndSay {

    @EpiTest(testDataFile = "look_and_say.tsv")
    public static String lookAndSay(int n) {
        String input = "1";
        if (n == 1)
            return input;

        for (int i = 1; i < n; i++) {
            input = generateNext(input);
        }
        return input;
    }

    private static String generateNext(String input) {
        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i - 1)) {
                count++;
            } else {
                result.append(count).append(input.charAt(i - 1));
                count = 1;
            }
        }
        return result.append(count).append(input.charAt(input.length() - 1)).toString();
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LookAndSay.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
