package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PathSum {

    @EpiTest(testDataFile = "path_sum.tsv")
    public static boolean hasPathSum(BinaryTreeNode<Integer> tree, int remainingWeight) {
        if (tree == null) {
            return false;
        } else if (tree.getLeft() == null && tree.getRight() == null) {
            return tree.getData() == remainingWeight;
        }

        remainingWeight = remainingWeight - tree.getData();
        return hasPathSum(tree.getLeft(), remainingWeight) || hasPathSum(tree.getRight(), remainingWeight);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PathSum.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
