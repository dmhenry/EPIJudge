package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class DirectoryPathNormalization {

    private static final String PATH_SEPARATOR = "/";
    private static final String PARENT_DIR = "..";
    private static final String CURR_DIR = ".";

    @EpiTest(testDataFile = "directory_path_normalization.tsv")
    public static String shortestEquivalentPath(String path) {
        Deque<String> stack = new ArrayDeque<>();

        if (path.startsWith(PATH_SEPARATOR)) {
            stack.push(PATH_SEPARATOR);
        }

        String[] pathComponents = path.split(PATH_SEPARATOR);

        for (String pathComponent : pathComponents) {
            if (PARENT_DIR.equals(pathComponent)) {
                if (stack.isEmpty() || PARENT_DIR.equals(stack.peek())) {
                    stack.push(pathComponent);
                } else {
                    if (PATH_SEPARATOR.equals(stack.peek())) {
                        throw new IllegalStateException();
                    }
                    stack.pop();
                }
            } else if (!pathComponent.isEmpty() && !CURR_DIR.equals(pathComponent)) {
                stack.push(pathComponent);
            }
        }

        StringBuilder result = new StringBuilder();
        Iterator<String> iter = stack.descendingIterator();
        String next;

        while (iter.hasNext()) {
            next = iter.next();
            result.append(next);
            if (!PATH_SEPARATOR.equals(next) && iter.hasNext()) {
                result.append(PATH_SEPARATOR);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }

}
