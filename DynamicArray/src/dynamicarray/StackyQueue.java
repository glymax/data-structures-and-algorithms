package dynamicarray;

public class StackyQueue implements Queue {

    private final DynamicStack first;
    private final DynamicStack second;

    public StackyQueue(int growthFactor, int maxOverhead) {
        first = new DynamicStack(growthFactor, maxOverhead);
        second = new DynamicStack(growthFactor, maxOverhead);
    }

    @Override
    public int size() {
        return first.size() + second.size();
    }

    @Override
    public void pushBack(int value) {
        first.pushBack(value);
    }

    @Override
    public int popFront() {
        if (second.isEmpty()) {
            while (!first.isEmpty()) {
                second.pushBack(first.popBack());
            }
        }
        return second.popBack();
    }

    @Override
    public String toString() {
        return first + ", " + second;
    }

    public static void main(String[] args) {
        StackyQueue stackyQueue = new StackyQueue(3, 4);
        stackyQueue.pushBack(1);//: [1, 0, 0] []
        stackyQueue.pushBack(2);// [1, 2, 0] []
        stackyQueue.pushBack(3);//: [1, 2, 3] []
        stackyQueue.pushBack(4);//: [1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0] []
        stackyQueue.popFront();//: [] [4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0] (Rückgabe 1)
        stackyQueue.popFront();//: [] [4, 3, 0, 0, 0, 0] (Rückgabe 2)
        stackyQueue.popFront();//: [] [4, 0, 0] (Rückgabe 3)
        stackyQueue.pushBack(5);//: [5, 0, 0] [4, 0, 0]
        stackyQueue.pushBack(6);//: [5, 6, 0] [4, 0, 0]
        stackyQueue.popFront();//: [5, 6, 0] [] (Rückgabe 4)
        stackyQueue.popFront();//: [] [6, 5, 0] (Rückgabe 5)
    }
}