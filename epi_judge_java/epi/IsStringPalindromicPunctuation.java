package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsStringPalindromicPunctuation {

    @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")
    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            char leftCh = s.charAt(left), rightCh = s.charAt(right);
            if (!Character.isLetterOrDigit(leftCh)) {
                left++;
            } else if (!Character.isLetterOrDigit(rightCh)) {
                right--;
            } else if (Character.toLowerCase(leftCh) != Character.toLowerCase(rightCh)) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
