package com.company;

public class Main {
    public static void preorder(BinaryTree<Integer> tree) {
        System.out.println(tree.getContent());
        if (tree.getLeft() != null) {
            preorder(tree.getLeft());
        }
        if (tree.getRight() != null) {
            preorder(tree.getRight());
        }
    }

    public static void inorder(BinaryTree<Integer> tree) {
        if (tree.getLeft() != null) {
            inorder(tree.getLeft());
        }
        System.out.println(tree.getContent());
        if (tree.getRight() != null) {
            inorder(tree.getRight());
        }
    }

    public static void postorder(BinaryTree<Integer> tree) {
        if (tree.getLeft() != null) {
            postorder(tree.getLeft());
        }
        if (tree.getRight() != null) {
            postorder(tree.getRight());
        }
        System.out.println(tree.getContent());
    }

    public static <G> int count(BinaryTree<G> tree, int num) {
        num += 1;
        if (tree.getLeft() != null) {
            num = count(tree.getLeft(), num);
        }
        if (tree.getRight() != null) {
            num = count(tree.getRight(), num);
        }
        return num;
    }

    public static void main(String[] args) {
        BinaryTree<Integer> b0 = new BinaryTree<>(20);
        BinaryTree<Integer> b1 = new BinaryTree<>(3);
        b1.setLeft(b0);
        BinaryTree<Integer> b2 = new BinaryTree<>(7);
        BinaryTree<Integer> b3 = new BinaryTree<>(6);
        b3.setRight(b2);
        BinaryTree<Integer> b4 = new BinaryTree<>(10);
        BinaryTree<Integer> b5 = new BinaryTree<>(11);
        b5.setLeft(b4);
        BinaryTree<Integer> b6 = new BinaryTree<>(9, b3, b5);

        BinaryTree<Integer> root = new BinaryTree<>(5, b1, b6);

        System.out.println(count(root, 0));
    }
}
