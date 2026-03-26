package dynamicarray;

public class DynamicStack implements Stack {

    private final DynamicArray array;
    private int elements = 0;

    public DynamicStack(int growthFactor, int maxOverhead) {
        array = new DynamicArray(growthFactor, maxOverhead);
    }

    @Override
    public int size() {
        return elements;
    }

    @Override
    public void pushBack(int value) {
        if (elements != 0) {
            array.reportUsage(new Interval.NonEmptyInterval(0, elements - 1), elements + 1);
        } else {
            array.reportUsage(Interval.EmptyInterval.getEmptyInterval(), elements + 1);
        }
        array.set(elements, value);
        elements++;
    }

    @Override
    public int popBack() {
        int back = array.get(elements - 1);
        if (elements == 1) {
            array.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 0);
        } else {
            array.reportUsage(new Interval.NonEmptyInterval(0, elements - 2), elements - 1);
        }
        elements--;
        return back;
    }

    @Override
    public String toString() {
        return array + ", length: " + size();
    }
}