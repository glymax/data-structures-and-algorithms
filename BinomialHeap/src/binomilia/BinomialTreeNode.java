package binomilia;

import java.util.ArrayList;
import java.util.List;

public class BinomialTreeNode {

    private final int element;

    private final List<BinomialTreeNode> children = new ArrayList<>();

    public BinomialTreeNode(int element) {
        this.element = element;
    }

    public int rank() {
        //return helpRank_iterative(this);
        return helpRank_recursive(this);
    }

    public int helpRank_iterative(BinomialTreeNode node) {
        int i = 0;
        for (; !node.children.isEmpty(); i++) {
            node = node.children.get(node.children.size() - 1);
        }
        return i;
    }

    public int helpRank_recursive(BinomialTreeNode node) {
        return node.children.isEmpty() ? 0 : 1 + helpRank_recursive(node.children.get(node.children.size() - 1));
    }

    public BinomialTreeNode getChildWithRank(int rank) {
        return children.get(rank);
    }

    public static BinomialTreeNode merge(BinomialTreeNode a, BinomialTreeNode b) {
        if (a.element < b.element) {
            a.children.add(b);
            return a;
        } else {
            b.children.add(a);
            return b;
        }
    }

    public List<BinomialTreeNode> getChildren() {
        return children;
    }

    public int getElement() {
        return element;
    }

    public int dotNode(StringBuilder sb, int idx) {
        sb.append(String.format("\t\t%d [label=\"%d\"];%n", idx, element));
        int rank = rank();
        int next = idx + 1;
        for (int i = 0; i < rank; i++) {
            next = getChildWithRank(i).dotLink(sb, idx, next);
        }
        return next;
    }

    private int dotLink(StringBuilder sb, int idx, int next) {
        sb.append(String.format("\t\t%d -> %d;%n", idx, next));
        return dotNode(sb, next);
    }
}