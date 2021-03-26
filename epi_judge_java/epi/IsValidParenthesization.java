package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;

public class IsValidParenthesization {

    @EpiTest(testDataFile = "is_valid_parenthesization.tsv")
    public static boolean isWellFormed(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        // loop variables
        char token;

        for (int i = 0; i < s.length(); i++) {
            token = s.charAt(i);
            if (isOpenBracket(token)) {
                stack.push(token);
            } else if (stack.isEmpty() || !isBracketPair(stack.pop(), token)) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    private static boolean isOpenBracket(char ch) {
        return (ch == '(' || ch == '[' || ch == '{');
    }

    private static boolean isBracketPair(char open, char close) {
        return (open == '(' && close == ')' || open == '[' && close == ']' || open == '{' && close == '}');
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }

}
