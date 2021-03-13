package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class RunLengthCompression {

    public static String decoding(String s) {
        StringBuilder result = new StringBuilder();
        int j, count;

        for (int i = 0; i < s.length(); i = j + 1) {
            j = i + 1;
            while (j < s.length() && Character.isDigit(s.charAt(j))) {
                j++;
            }
            count = Integer.parseInt(s.substring(i, j));
            while (count > 0) {
                result.append(s.charAt(j));
                count--;
            }
        }
        return result.toString();
    }

    public static String encoding(String s) {
        StringBuilder result = new StringBuilder();
        int count = 1, i;

        for (i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                result.append(count).append(s.charAt(i - 1));
                count = 1;
            }
        }
        return result.append(count).append(s.charAt(i - 1)).toString();
    }

    @EpiTest(testDataFile = "run_length_compression.tsv")
    public static void rleTester(String encoded, String decoded)
            throws TestFailure {
        if (!decoding(encoded).equals(decoded)) {
            throw new TestFailure("Decoding failed");
        }
        if (!encoding(decoded).equals(encoded)) {
            throw new TestFailure("Encoding failed");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RunLengthCompression.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
