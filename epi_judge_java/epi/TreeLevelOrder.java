package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeLevelOrder {

    @EpiTest(testDataFile = "tree_level_order.tsv")
    public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
        List<List<Integer>> levels = new ArrayList<>();
        if (tree == null) {
            return levels;
        }

        Queue<BinaryTreeNode<Integer>> q = new ArrayDeque<>();
        q.offer(tree);

        // loop variables
        BinaryTreeNode<Integer> curr;
        List<Integer> level;

        while (!q.isEmpty()) {
            int count = q.size();
            level = new ArrayList<>();

            for (int i = 0; i < count; i++) {
                curr = q.poll();
                if (curr.left != null) {
                    q.offer(curr.left);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }
                level.add(curr.data);
            }
            levels.add(level);
        }
        return levels;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }

}
