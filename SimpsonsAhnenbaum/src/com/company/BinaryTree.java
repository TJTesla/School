package com.company;

public class BinaryTree<ContentType> {
    private class BTNode {
        private ContentType nodeContent;

        public BTNode(ContentType pContent) {
            nodeContent = pContent;
        }

        public ContentType getNodeContent() {
            return nodeContent;
        }
    }

    private BTNode content;

    private BinaryTree<ContentType> left;
    private  BinaryTree<ContentType> right;

    public BinaryTree(ContentType pContent) {
        if (pContent != null) {
            content = new BTNode(pContent);
        }
    }
    public BinaryTree(ContentType pContent, BinaryTree<ContentType> leftBranch, BinaryTree<ContentType> rightBranch) {
        if (pContent != null) {
            content = new BTNode(pContent);
        }
        if (leftBranch != null) {
            left = leftBranch;
        }
        if (rightBranch != null) {
            right = rightBranch;
        }
    }

    public boolean isEmpty() {
        return (content == null);
    }

    public void setContent(ContentType pContent) {
        if (pContent != null) {
            content = new BTNode(pContent);
        }
    }
    public void setLeft(BinaryTree<ContentType> leftBranch) {
        if (leftBranch != null) {
            left = leftBranch;
        }
    }
    public void setRight(BinaryTree<ContentType> rightBranch) {
        if (rightBranch != null) {
            right = rightBranch;
        }
    }

    public ContentType getContent() {
        if (content == null) {
            return null;
        } else {
            return content.getNodeContent();
        }
    }
    public BinaryTree<ContentType> getLeft() {
        if (left == null) {
            return null;
        } else {
            return left;
        }
    }
    public BinaryTree<ContentType> getRight() {
        if (right == null) {
            return null;
        } else {
            return right;
        }
    }
}
