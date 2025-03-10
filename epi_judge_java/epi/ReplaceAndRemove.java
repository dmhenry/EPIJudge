package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;

public class ReplaceAndRemove {

    public static int replaceAndRemove(int size, char[] s) {
        int writeIdx = 0, aCount = 0;
        for (int i = 0; i < size; i++) {
            if (s[i] != 'b') {
                s[writeIdx++] = s[i];
            }
            if (s[i] == 'a') {
                aCount++;
            }
        }

        int currIdx = writeIdx - 1;
        writeIdx = writeIdx + aCount - 1;
        final int resultingSize = writeIdx + 1;

        while (currIdx >= 0) {
            if (s[currIdx] == 'a') {
                s[writeIdx--] = 'd';
                s[writeIdx--] = 'd';
            } else {
                s[writeIdx--] = s[currIdx];
            }
            currIdx--;
        }
        return resultingSize;
    }

    @EpiTest(testDataFile = "replace_and_remove.tsv")
    public static List<String>
    replaceAndRemoveWrapper(TimedExecutor executor, Integer size, List<String> s)
            throws Exception {
        char[] sCopy = new char[s.size()];
        for (int i = 0; i < size; ++i) {
            if (!s.get(i).isEmpty()) {
                sCopy[i] = s.get(i).charAt(0);
            }
        }

        Integer resSize = executor.run(() -> replaceAndRemove(size, sCopy));

        List<String> result = new ArrayList<>();
        for (int i = 0; i < resSize; ++i) {
            result.add(Character.toString(sCopy[i]));
        }
        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReplaceAndRemove.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
