package binomilia;

import java.util.*;

public class BinomialHeap {

    private BinomialTreeNode Root;
    private final Map<Integer, BinomialTreeNode> Heap = new HashMap<>();

    public BinomialHeap() {
    }

    public int min() {
        if (Root == null) {
            throw new NoSuchElementException();
        }
        return Root.getElement();
    }

    public void insert(int key) {
        BinomialTreeNode node = new BinomialTreeNode(key);
        if (Root == null || key < Root.getElement()) {
            Root = node;
        }
        int i = 0;
        for (; Heap.containsKey(i); i++) {
            node = BinomialTreeNode.merge(node, Heap.remove(i));
        }
        Heap.put(i, node);
    }

    public int deleteMin() {
        if (Root == null) {
            throw new NoSuchElementException();
        }
        int ret = Heap.remove(Root.rank()).getElement();
        List<BinomialTreeNode> log = Root.getChildren();
        BinomialTreeNode node;
        int i = log.size() - 1;
        int b;
        for (; i >= 0; i--) {
            b = i;
            node = log.remove(b);
            for (; Heap.containsKey(b); b++) {
                node = BinomialTreeNode.merge(node, Heap.remove(b));
            }
            Heap.put(b, node);
        }
        if (!Heap.isEmpty()) {
            Root = Heap.values().stream().min(Comparator.comparingInt(BinomialTreeNode::getElement)).get();
        }
        return ret;
    }

    public static String dot(BinomialTreeNode[] trees) {
        return dot(Arrays.stream(trees).toList());
    }

    public static String dot(Collection<BinomialTreeNode> trees) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {").append(System.lineSeparator());
        int id = 0;
        List<Integer> roots = new ArrayList<>();
        for (BinomialTreeNode tree : trees) {
            sb.append(String.format("\tsubgraph cluster_%d {%n", id));
            roots.add(id);
            id = tree.dotNode(sb, id);
            sb.append(String.format("\t}%n"));
        }
        for (int i = 0; i < roots.size() - 1; i++) {
            sb.append(String.format("\t%d -> %d [constraint=false,style=dashed];%n", roots.get(i), roots.get(i + 1)));
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return dot(Heap.values());
    }


    public static void main(String[] args) {
        BinomialHeap binomialHeap = new BinomialHeap();
        binomialHeap.insert(0);
        binomialHeap.insert(1);
        binomialHeap.insert(2);
        binomialHeap.insert(1);
        binomialHeap.insert(2);
        binomialHeap.insert(3);
        binomialHeap.insert(4);
        System.out.println(binomialHeap);
    }

    public static void performanceTest1(BinomialHeap heap) {
        long start_time = System.nanoTime();
        Random random = new Random(400);
        for (int i = 0; i < 5_000; i++) {
            heap.insert(random.nextInt());
        }
        long end_time = System.nanoTime();
        System.out.println("Performance test 1: " + (end_time - start_time) / 1e6 + "ms");
    }

    public static void performanceTest2(BinomialHeap heap) {
        long start_time = System.nanoTime();
        Random random = new Random(400);
        for (int i = 0; i < 2_500; i++) {
            heap.insert(random.nextInt());
        }

        for (int i = 2_500; i > 0; i--) {
            heap.deleteMin();
        }
        long end_time = System.nanoTime();
        System.out.println("Performance test 2: " + (end_time - start_time) / 1e6 + "ms");
    }

    public static void performanceTest3(BinomialHeap heap) {
        long start_time = System.nanoTime();
        Random random = new Random(400);
        for (int i = 0; i < 1_250; i++) {
            heap.insert(random.nextInt());
        }

        for (int i = 1_000; i > 0; i--) {
            heap.deleteMin();
        }

        for (int i = 0; i < 1_250; i++) {
            heap.insert(random.nextInt());
        }

        for (int i = 1_500; i > 0; i--) {
            heap.deleteMin();
        }
        long end_time = System.nanoTime();
        System.out.println("Performance test 3: " + (end_time - start_time) / 1e6 + "ms");
    }
}