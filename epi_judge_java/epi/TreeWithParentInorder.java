package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class TreeWithParentInorder {

    @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")
    public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
        List<Integer> inorder = new ArrayList<>();
        BinaryTree<Integer> prev = null, next = null, curr = tree;

        while (curr != null) {
            if (prev == curr.parent) {
                if (curr.left != null) {
                    next = curr.left;
                } else {
                    inorder.add(curr.data);
                    next = (curr.right != null) ? curr.right : curr.parent;
                }
            } else if (prev == curr.left) {
                inorder.add(curr.data);
                next = (curr.right != null) ? curr.right : curr.parent;
            } else { // prev == curr.right
                next = curr.parent;
            }

            prev = curr;
            curr = next;
        }
        return inorder;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
