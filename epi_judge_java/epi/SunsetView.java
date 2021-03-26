package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SunsetView {

    public static List<Integer> examineBuildingsWithSunset(Iterator<Integer> sequence) {
        Deque<BuildingHeight> stack = new ArrayDeque<>();
        Integer height;
        int idx = 0;

        while (sequence.hasNext()) {
            height = sequence.next();
            BuildingHeight building = new BuildingHeight(idx++, height);

            while (!stack.isEmpty() && building.compareTo(stack.peek()) >= 0) {
                stack.pop();
            }
            stack.push(building);
        }
        
        List<Integer> result = new LinkedList<>();

        for (BuildingHeight buildingHeight : stack) {
            result.add(buildingHeight.id);
        }
        return result;
    }

    private static class BuildingHeight implements Comparable<BuildingHeight> {
        
        private Integer id;
        private Integer height;

        private BuildingHeight(Integer id, Integer height) {
            this.id = id;
            this.height = height;
        }

        @Override
        public int compareTo(BuildingHeight that) {
            return Integer.compare(this.height, that.height);
        }

    }

    @EpiTest(testDataFile = "sunset_view.tsv")
    public static List<Integer> examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
        return examineBuildingsWithSunset(sequence.iterator());
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SunsetView.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }

}
