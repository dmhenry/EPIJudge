package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class KthNodeInTree {

    public static class BinaryTreeNode<T> extends TreeLike<T, BinaryTreeNode<T>> {
        public T data;
        public BinaryTreeNode<T> left, right;
        public int size;

        public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right, int size) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.size = size;
        }

        @Override
        public T getData() {
            return data;
        }

        @Override
        public BinaryTreeNode<T> getLeft() {
            return left;
        }

        @Override
        public BinaryTreeNode<T> getRight() {
            return right;
        }
    }

    public static BinaryTreeNode<Integer> findKthNodeBinaryTree(BinaryTreeNode<Integer> tree, int k) {
        int leftSize = (tree.getLeft() == null) ? 0 : tree.getLeft().size;
        if (k - leftSize == 1) {
            return tree;
        } else if (k - leftSize < 1) {
            assert(tree.getLeft() != null); // If tree.getLeft() == null, then leftSize > 0
            return findKthNodeBinaryTree(tree.getLeft(), k);
        }
        return findKthNodeBinaryTree(tree.getRight(), k - leftSize - 1);
    }

    public static BinaryTreeNode<Integer> convertToTreeWithSize(BinaryTree<Integer> original) {
        if (original == null)
            return null;
        BinaryTreeNode<Integer> left = convertToTreeWithSize(original.left);
        BinaryTreeNode<Integer> right = convertToTreeWithSize(original.right);
        int lSize = left == null ? 0 : left.size;
        int rSize = right == null ? 0 : right.size;
        return new BinaryTreeNode<>(original.data, left, right, 1 + lSize + rSize);
    }

    @EpiTest(testDataFile = "kth_node_in_tree.tsv")
    public static int findKthNodeBinaryTreeWrapper(TimedExecutor executor,
                                                   BinaryTree<Integer> tree,
                                                   int k) throws Exception {
        BinaryTreeNode<Integer> converted = convertToTreeWithSize(tree);

        BinaryTreeNode<Integer> result =
                executor.run(() -> findKthNodeBinaryTree(converted, k));

        if (result == null) {
            throw new TestFailure("Result can't be null");
        }
        return result.data;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KthNodeInTree.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
