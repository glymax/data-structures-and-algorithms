package radix;

import java.util.Arrays;
import java.util.Random;

public final class BinaryRadixSort {

    private BinaryRadixSort() {
    }

    public static int key(int element, int binPlace) {
        if (element < 0) {
            return ((element >> binPlace) & 1) == 0 ? 0 : 1;
        } else {
            return (element >> binPlace) & 1;
        }
    }

    public static void kSort(BinaryBucket from, BinaryBucket to, int binPlace) {
        int size = from.getSize();
        if (binPlace > 0) {
            int mid = from.getMid();
            for (int i = 0; i < mid; i++) {
                if (key(from.getBucket()[i], binPlace) == 0) {
                    to.insertLeft(from.getBucket()[i]);
                } else {
                    to.insertRight(from.getBucket()[i]);
                }
            }
            for (int i = size - 1; i >= mid; i--) {
                if (key(from.getBucket()[i], binPlace) == 0) {
                    to.insertLeft(from.getBucket()[i]);
                } else {
                    to.insertRight(from.getBucket()[i]);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (key(from.getBucket()[i], binPlace) == 0) {
                    to.insertLeft(from.getBucket()[i]);
                } else {
                    to.insertRight(from.getBucket()[i]);
                }
            }
        }
    }

    public static void lastSort(BinaryBucket from, int[] to) {
        int size = from.getSize();
        if (from.getBucket()[size - 1] < 0) {
            int mid = from.getMid();
            int u = 0;
            for (int i = size - 1; i >= mid; i--) {
                to[u] = from.getBucket()[i];
                u++;
            }
            for (int i = 0; i < mid; i++) {
                to[u] = from.getBucket()[i];
                u++;
            }
        } else {
            for (int i = 0; i < to.length; i++) {
                to[i] = from.getBucket()[i];
            }
        }
    }

    public static void sort(int[] elements) {
        if (elements.length == 0) {
            return;
        }
        int size = elements.length;
        BinaryBucket binaryBucket1 = new BinaryBucket(size);
        binaryBucket1.setBucket(elements);
        BinaryBucket binaryBucket2 = new BinaryBucket(size);
        for (int i = 0; i < 32; i++) {
            if (i % 2 == 0) {
                binaryBucket2.nullen();
                kSort(binaryBucket1, binaryBucket2, i);
            } else {
                binaryBucket1.nullen();
                kSort(binaryBucket2, binaryBucket1, i);
            }

        }
        lastSort(binaryBucket1, elements);
    }

    public static void main(String[] args) {
        int[] test = new int[10_000_000];
        Random random = new Random();
        for (int i = 0; i < test.length; i++) {
            test[i] = random.nextInt(Integer.MAX_VALUE);
        }
        int[] testTwo = Arrays.copyOf(test, test.length);

        long start = System.nanoTime();
        sort(test);
        long binaryTime = System.nanoTime() - start;

        start = System.nanoTime();
        RadixSort.sort(testTwo);
        long decimalTime = System.nanoTime() - start;

        System.out.println("correctly sorted:" + Arrays.equals(test, testTwo));
        System.out.println("Binary: " + binaryTime / 1e6 + "ms");
        System.out.println("Decimal: " + decimalTime / 1e6 + "ms");
    }
}
