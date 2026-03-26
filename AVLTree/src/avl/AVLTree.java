package avl;

import java.util.HashSet;
import java.util.Set;

public class AVLTree {
    private AVLTreeNode root = null;

    public AVLTree() {
    }

    public AVLTreeNode getRoot() {
        return root;
    }

    public void setRoot(AVLTreeNode root) {
        this.root = root;
    }

    public int height() {
        return root == null ? 0 : root.height();
    }

    public boolean validAVL() {
        try {
            return validHelp(root, new HashSet<>(), Integer.MIN_VALUE, Integer.MAX_VALUE);
        } catch (Throwable e) {
            return false;
        }
    }

    public boolean validHelp(AVLTreeNode node, Set<AVLTreeNode> visited, int min, int max) {
        if (node == null) {
            return true;
        }
        int balance = node.getBalance();
        int leftHigh = node.getLeft() != null ? node.getLeft().height() : 0;
        int rightHigh = node.getRight() != null ? node.getRight().height() : 0;

        if (balance != rightHigh - leftHigh || Math.abs(node.getBalance()) > 1 || node.getKey() > max || node.getKey() < min || visited.contains(node)) {
            return false;
        }
        visited.add(node);
        return validHelp(node.getLeft(), visited, min, node.getKey()) && validHelp(node.getRight(), visited, node.getKey(), max);
    }

    public void insert(int key) {
        root = helpInsert(key, root);
    }

    public AVLTreeNode helpInsert(int key, AVLTreeNode currentNode) {
        if (currentNode == null) {
            return new AVLTreeNode(key);
        } else if (key < currentNode.getKey()) {
            currentNode.setLeft(helpInsert(key, currentNode.getLeft()));
        } else {
            currentNode.setRight(helpInsert(key, currentNode.getRight()));
        }
        updateBalance(currentNode);
        return helpBalance(currentNode, key);
    }

    public AVLTreeNode helpBalance(AVLTreeNode currentNode, int key) {
        int newBalance = currentNode.getBalance();

        if (newBalance == 0 || Math.abs(newBalance) == 1) {
            return currentNode;
        }
        AVLTreeNode toBalance = key < currentNode.getKey() ? currentNode.getLeft() : currentNode.getRight();

        if (Math.abs(newBalance) == 2) {
            if (newBalance >= 0 && toBalance.getBalance() >= 0 || newBalance < 0 && toBalance.getBalance() < 0) {
                currentNode = newBalance < 0 ? rechtsRotation(currentNode, toBalance) : linksRotation(currentNode, toBalance);
            } else {
                currentNode = newBalance > 0 ? linksRotation(currentNode, rechtsRotation(toBalance, toBalance.getLeft())) : rechtsRotation(currentNode, linksRotation(toBalance, toBalance.getRight()));
            }
        }
        return currentNode;
    }

    public AVLTreeNode linksRotation(AVLTreeNode wurzel, AVLTreeNode node) {
        AVLTreeNode left = node.getLeft();
        node.setLeft(wurzel);
        wurzel.setRight(left);
        updateBalance(wurzel);
        updateBalance(node);
        return node;
    }

    public AVLTreeNode rechtsRotation(AVLTreeNode wurzel, AVLTreeNode node) {
        AVLTreeNode right = node.getRight();
        node.setRight(wurzel);
        wurzel.setLeft(right);
        updateBalance(wurzel);
        updateBalance(node);
        return node;
    }

    public void updateBalance(AVLTreeNode currentNode) {
        currentNode.setBalance(((currentNode.getRight() != null) ? currentNode.getRight().height() : 0) - ((currentNode.getLeft() != null) ? currentNode.getLeft().height() : 0));
    }

    public boolean find(int key) {
        return helpFind(root, key);
    }

    public boolean helpFind(AVLTreeNode node, int key) {
        if (node == null) {
            return false;
        }
        if (node.getKey() == key) {
            return true;
        }
        return key < node.getKey() ? helpFind(node.getLeft(), key) : helpFind(node.getRight(), key);
    }

    private String dot() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {" + System.lineSeparator());
        if (root != null) {
            root.dot(sb);
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return dot();
    }

    public static void main(String[] a) {
        AVLTree avlTree = new AVLTree();
        System.out.println(avlTree.height());
        avlTree.insert(15);
        avlTree.insert(5);
        avlTree.insert(20);
        avlTree.insert(4);
        avlTree.insert(6);
        avlTree.insert(18);
        avlTree.insert(23);
        avlTree.insert(3);
        avlTree.insert(2);
        avlTree.insert(15);
        avlTree.insert(20);
        System.out.println(avlTree.dot());
    }
}