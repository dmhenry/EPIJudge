package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TreeInorder {

    private static class NodeAndState {
        public BinaryTreeNode<Integer> node;
        public Boolean leftSubtreeTraversed;

        public NodeAndState(BinaryTreeNode<Integer> node,
                            Boolean leftSubtreeTraversed) {
            this.node = node;
            this.leftSubtreeTraversed = leftSubtreeTraversed;
        }
    }

    @EpiTest(testDataFile = "tree_inorder.tsv")
    public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
        Deque<BinaryTreeNode<Integer>> stack = new ArrayDeque<>();
        List<Integer> inorder = new ArrayList<>();

        while (tree != null || !stack.isEmpty()) {
            while (tree != null) {
                stack.push(tree);
                tree = tree.getLeft();
            }
            tree = stack.pop();
            inorder.add(tree.getData());
            tree = tree.getRight();
        }
        return inorder;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
