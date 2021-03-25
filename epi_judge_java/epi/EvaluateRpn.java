package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiFunction;

public class EvaluateRpn {

    @EpiTest(testDataFile = "evaluate_rpn.tsv")
    public static int eval(String expression) {
        Deque<Integer> stack = new ArrayDeque<>();
        String[] tokens = expression.split("\\s*,\\s*");

        Integer op0, op1;
        for (String token : tokens) {
            Operator op = Operator.of(token);
            if (op == null) {
                stack.push(Integer.parseInt(token));
            } else {
                op1 = stack.pop();
                op0 = stack.pop();
                stack.push(op.fn.apply(op0, op1));
            }
        }
        return (stack.isEmpty()) ? -1 : stack.pop();
    }

    private enum Operator {
        ADD((addend, augend) -> addend + augend),
        SUB((minuend, subtrahend) -> minuend - subtrahend),
        MULT((multiplicand, multiplier) -> multiplicand * multiplier),
        DIV((dividend, divisor) -> dividend / divisor);

        Operator(BiFunction<Integer, Integer, Integer> fn) {
            this.fn = fn;
        }

        private final BiFunction<Integer, Integer, Integer> fn;

        private static Operator of(String token) {
            switch (token) {
                case "+":
                    return ADD;
                case "-":
                    return SUB;
                case "*":
                    return MULT;
                case "/":
                    return DIV;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }

}
