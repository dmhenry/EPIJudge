package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestor {

    public static BinaryTreeNode<Integer> lca(BinaryTreeNode<Integer> tree,
                                              BinaryTreeNode<Integer> node0,
                                              BinaryTreeNode<Integer> node1) {

        List<BinaryTreeNode<Integer>> path0 = new ArrayList<>();
        List<BinaryTreeNode<Integer>> path1 = new ArrayList<>();

        findPath(tree, node0, path0);
        findPath(tree, node1, path1);

        List<BinaryTreeNode<Integer>> shorter = (path1.size() < path0.size()) ? path1 : path0;
        BinaryTreeNode<Integer> result = tree;

        int i;
        for (i = 1; i < shorter.size(); i++) {
            if (!path0.get(i).equals(path1.get(i))) {
                result = path0.get(i - 1);
                break;
            }
        }
        if (i == shorter.size()) {
            result = shorter.get(i - 1);
        }
        return result;
    }

    private static boolean findPath(BinaryTreeNode<Integer> tree, BinaryTreeNode<Integer> key, List<BinaryTreeNode<Integer>> path) {
        if (tree == null)
            return false;

        path.add(tree);

        if (key.getData().equals(tree.getData()))
            return true;

        if (findPath(tree.getLeft(), key, path) || findPath(tree.getRight(), key, path))
            return true;

        path.remove(path.size() - 1);

        return false;
    }

    @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
    public static int lcaWrapper(TimedExecutor executor,
                                 BinaryTreeNode<Integer> tree, Integer key0,
                                 Integer key1) throws Exception {
        BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
        BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

        BinaryTreeNode<Integer> result =
                executor.run(() -> lca(tree, node0, node1));

        if (result == null) {
            throw new TestFailure("Result can not be null");
        }
        return result.data;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
