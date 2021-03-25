package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidIpAddresses {
    
    @EpiTest(testDataFile = "valid_ip_addresses.tsv")
    public static List<String> getValidIpAddress(String s) {
        List<String> result = new ArrayList<>();
        String[] quartets = new String[4];

        for (int i = 1; i <= 3 && i + 2 < s.length(); i++) {
            quartets[0] = s.substring(0, i);
            if (!isValidQuartet(quartets[0]))
                continue;

            for (int j = 1; j <= 3 && (i + j + 1) < s.length(); j++) {
                quartets[1] = s.substring(i, i + j);
                if (!isValidQuartet(quartets[1]))
                    continue;

                for (int k = 1; k <= 3 && (i + j + k) < s.length(); k++) {
                    quartets[2] = s.substring(i + j, i + j + k);
                    quartets[3] = s.substring(i + j + k);
                    if (!isValidQuartet(quartets[2]) || !isValidQuartet(quartets[3]))
                        continue;

                    result.add(quartets[0] + "." + quartets[1] + "." + quartets[2] + "." + quartets[3]);
                }
            }
        }
        return result;
    }

    private static boolean isValidQuartet(String ipQuartet) {
        return !ipQuartet.isEmpty() && ipQuartet.length() <= 3
                && (!ipQuartet.startsWith("0") || ipQuartet.length() <= 1)
                && Integer.parseInt(ipQuartet) <= 255;
    }

    @EpiTestComparator
    public static boolean comp(List<String> expected, List<String> result) {
        if (result == null) {
            return false;
        }
        Collections.sort(expected);
        Collections.sort(result);
        return expected.equals(result);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ValidIpAddresses.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
