package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class SuccessorInTree {

    public static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {
        // Return left-most node in right sub-tree
        if (node.getRight() != null) {
            node = node.getRight();
            while (node.getLeft() != null) {
                node = node.getLeft();
            }
            return node;
        }

        // Find first ancestor for which this node is in its parent's left sub-tree
        while (node.parent != null && node == node.parent.getRight()) {
            node = node.parent;
        }
        return node.parent;
    }

    @EpiTest(testDataFile = "successor_in_tree.tsv")
    public static int findSuccessorWrapper(TimedExecutor executor,
                                           BinaryTree<Integer> tree, int nodeIdx)
            throws Exception {
        BinaryTree<Integer> n = BinaryTreeUtils.mustFindNode(tree, nodeIdx);

        BinaryTree<Integer> result = executor.run(() -> findSuccessor(n));

        return result == null ? -1 : result.data;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SuccessorInTree.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
