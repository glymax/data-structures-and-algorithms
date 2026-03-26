package radix;

import java.util.Arrays;

public class BinaryBucket {

    private int[] bucket;
    private int start;
    private int end;

    public BinaryBucket(int size) {
        bucket = new int[size];
        start = 0;
        end = size - 1;
    }

    public void insertLeft(int number) {
        bucket[start] = number;
        start++;
    }

    public void insertRight(int number) {
        bucket[end] = number;
        end--;
    }

    public int getMid() {
        return start;
    }

    public void setBucket(int[] bucket) {
        this.bucket = Arrays.copyOf(bucket, bucket.length);
    }

    public int getSize() {
        return bucket.length;
    }

    public int[] getBucket() {
        return bucket;
    }

    public void nullen() {
        start = 0;
        end = bucket.length - 1;
    }
}
