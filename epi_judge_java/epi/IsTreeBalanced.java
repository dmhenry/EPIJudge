package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeBalanced {

    @EpiTest(testDataFile = "is_tree_balanced.tsv")
    public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
        return (balanceHeight(tree) >= 0);
    }

    private static int balanceHeight(BinaryTreeNode<Integer> tree) {
        if (tree == null)
            return 0;

        int leftHeight = balanceHeight(tree.left);
        if (leftHeight < 0)
            return leftHeight;

        int rightHeight = balanceHeight(tree.right);
        if (rightHeight < 0)
            return rightHeight;

        return (Math.abs(leftHeight - rightHeight) > 1) ? -1 : 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }

}
