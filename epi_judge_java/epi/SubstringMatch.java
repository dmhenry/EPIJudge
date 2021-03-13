package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SubstringMatch {

    private static final int ALPHA_SIZE = 26;
    private static final int PRIME = 997;

    // Returns the index of the first character of the substring if found, -1
    // otherwise.
    @EpiTest(testDataFile = "substring_match.tsv")
    public static int rabinKarp(String t, String s) {
        if (s.length() > t.length()) {
            return -1;
        }

        int magnitude = 1, tHash = 0, sHash = 0;
        for (int i = 0; i < s.length(); i++) {
            magnitude = (i > 0) ? mod(magnitude * ALPHA_SIZE) : 1;
            tHash = mod(mod(ALPHA_SIZE * tHash) + (t.charAt(i) - 'a'));
            sHash = mod(mod(ALPHA_SIZE * sHash) + (s.charAt(i) - 'a'));
        }

        int windowCount = t.length() - s.length() + 1;
        for (int i = 0; i <= windowCount; i++) {
            if (tHash == sHash && t.startsWith(s, i)) {
                return i;
            }
            if (i < windowCount - 1) {
                int rollOffAmount = mod(magnitude * (t.charAt(i) - 'a'));
                tHash = mod(mod(ALPHA_SIZE * mod(tHash - rollOffAmount)) + (t.charAt(i + s.length()) - 'a'));
            }
        }
        return -1;
    }

    private static int mod(int dividend) {
        int result = dividend % PRIME;
        if (result < 0) {
            result += PRIME;
        }
        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SubstringMatch.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
