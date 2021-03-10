package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class ReverseWords {

    public static void reverseWords(char[] input) {
        int left = 0, right = 0;
        while (left < input.length) {
            while (right < input.length && !Character.isSpaceChar(input[right]))
                right++;

            reverse(input, left, right - 1);
            left = ++right;
        }
        reverse(input, 0, input.length - 1);
    }

    private static void reverse(char[] input, int left, int right) {
        while (left < right) {
            char temp      = input[left];
            input[left++]  = input[right];
            input[right--] = temp;
        }
    }

    @EpiTest(testDataFile = "reverse_words.tsv")
    public static String reverseWordsWrapper(TimedExecutor executor, String s)
            throws Exception {
        char[] sCopy = s.toCharArray();

        executor.run(() -> reverseWords(sCopy));

        return String.valueOf(sCopy);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
