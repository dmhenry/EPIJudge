package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeSymmetric {

    @EpiTest(testDataFile = "is_tree_symmetric.tsv")
    public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
        return (tree == null) || isSymmetric(tree.left, tree.right);
    }

    private static boolean isSymmetric(BinaryTreeNode<Integer> a, BinaryTreeNode<Integer> b) {
        return (a == null && b == null) || (a != null && b != null && a.data.equals(b.data)
                && isSymmetric(a.left, b.right) && isSymmetric(a.right, b.left));
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
