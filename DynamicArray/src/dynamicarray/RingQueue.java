package dynamicarray;

public class RingQueue implements Queue {

    private final DynamicArray array;
    private int elements = 0;

    private int start = 0;
    private int end = 0;

    public RingQueue(int growthFactor, int maxOverhead) {
        array = new DynamicArray(growthFactor, maxOverhead);
    }

    @Override
    public int size() {
        return elements;
    }

    @Override
    public void pushBack(int value) {
        if (elements == array.getLength()) {
            if (elements == 0) {
                array.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 1);
                start = 0;
                end = 0;
            } else {
                array.reportUsage(new Interval.NonEmptyInterval(start, end), elements + 1);
                start = 0;
                end = elements;
            }
        } else {
            end = (end + 1) % array.getLength();
        }
        array.set(end, value);
        elements++;
    }

    @Override
    public int popFront() {
        int back = array.get(start);
        start = (start + 1) % array.getLength();
        if (elements == 1) {
            array.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 0);
            start = 0;
            end = 0;
        } else {
            Interval interval = array.reportUsage(new Interval.NonEmptyInterval(start, end), elements - 1);
            start = interval.getFrom();
            end = interval.getTo();
        }
        elements--;
        return back;
    }

    @Override
    public String toString() {
        return array + ", size: " + size();
    }

}