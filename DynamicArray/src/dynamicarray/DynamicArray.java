package dynamicarray;

import java.util.Arrays;

public class DynamicArray {

    private int[] elements;
    private final int growthFactor;
    private final int maxOverhead;

    public DynamicArray(int growthFactor, int maxOverhead) {
        if (growthFactor <= 0 || maxOverhead <= 0 || maxOverhead < growthFactor) {
            throw new IllegalArgumentException();
        }
        elements = new int[0];
        this.growthFactor = growthFactor;
        this.maxOverhead = maxOverhead;
    }

    public int getLength() {
        return elements.length;
    }

    public Interval reportUsage(Interval usage, int minSize) {
        int oldLength = elements.length;
        if (minSize > oldLength || minSize * maxOverhead < oldLength) {
            int[] elementum = new int[minSize * growthFactor];
            if (!usage.isEmpty()) {
                int from = usage.getFrom();
                int to = usage.getTo();
                int i = 0;
                elementum[0] = elements[from];
                while (from != to) {
                    i++;
                    from = (from + 1) % oldLength;
                    elementum[i] = elements[from];
                }
                usage = new Interval.NonEmptyInterval(0, i);
            }
            elements = elementum;
        }
        return usage;
    }

    public int get(int index) {
        return elements[index];
    }

    public void set(int index, int value) {
        elements[index] = value;
    }


    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}