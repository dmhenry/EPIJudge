package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class StackWithMax {

    public static class Stack {

        private final Deque<IntWithMax> stack = new ArrayDeque<>();

        public boolean empty() {
            return stack.isEmpty();
        }

        public Integer max() {
            if (stack.isEmpty())
                throw new IllegalStateException();
            return stack.peek().max;
        }

        public Integer pop() {
            if (stack.isEmpty())
                throw new IllegalStateException();
            return stack.pop().value;
        }

        public void push(Integer x) {
            Integer max;
            if (empty() || Integer.compare(Objects.requireNonNull(stack.peek()).max, x) < 0) {
                max = x;
            } else {
                max = stack.peek().max;
            }
            stack.push(new IntWithMax(x, max));
        }

        private static class IntWithMax {
            
            private final Integer value;
            private final Integer max;
            
            private IntWithMax(Integer value, Integer max) {
                if (value == null || max == null)
                    throw new IllegalArgumentException();

                this.value = value;
                this.max = max;
            }

        }

    }

    @EpiUserType(ctorParams = {String.class, int.class})
    public static class StackOp {
        public String op;
        public int arg;

        public StackOp(String op, int arg) {
            this.op = op;
            this.arg = arg;
        }
    }

    @EpiTest(testDataFile = "stack_with_max.tsv")
    public static void stackTester(List<StackOp> ops) throws TestFailure {
        try {
            Stack s = new Stack();
            int result;
            for (StackOp op : ops) {
                switch (op.op) {
                    case "Stack":
                        s = new Stack();
                        break;
                    case "push":
                        s.push(op.arg);
                        break;
                    case "pop":
                        result = s.pop();
                        if (result != op.arg) {
                            throw new TestFailure("Pop: expected " + String.valueOf(op.arg) +
                                    ", got " + String.valueOf(result));
                        }
                        break;
                    case "max":
                        result = s.max();
                        if (result != op.arg) {
                            throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                                    ", got " + String.valueOf(result));
                        }
                        break;
                    case "empty":
                        result = s.empty() ? 1 : 0;
                        if (result != op.arg) {
                            throw new TestFailure("Empty: expected " + String.valueOf(op.arg) +
                                    ", got " + String.valueOf(s));
                        }
                        break;
                    default:
                        throw new RuntimeException("Unsupported stack operation: " + op.op);
                }
            }
        } catch (NoSuchElementException e) {
            throw new TestFailure("Unexpected NoSuchElement exception");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StackWithMax.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
