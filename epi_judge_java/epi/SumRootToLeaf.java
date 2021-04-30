package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class SumRootToLeaf {

    @EpiTest(testDataFile = "sum_root_to_leaf.tsv")
    public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
        return sumRootToLeaf(tree, 0);
    }

    private static int sumRootToLeaf(BinaryTreeNode<Integer> tree, int partialSum) {
        if (tree == null)
            return 0;

        partialSum = 2 * partialSum + tree.getData();

        if (tree.left == null && tree.right == null)
            return partialSum;

        return sumRootToLeaf(tree.left, partialSum) + sumRootToLeaf(tree.right, partialSum);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SumRootToLeaf.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
