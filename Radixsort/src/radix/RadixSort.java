package radix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class RadixSort {
    private static final int DIGITS = 10;

    private RadixSort() {
    }

    public static int key(int element, int decPlace) {
        return (element / (int) Math.pow(10, decPlace)) % 10;
    }

    public static int getMaxDecimalPlaces(int[] elements) {
        /*int digits = 0;
        for (int element : elements) {
            int currentdigits = 1;
            while ((int) (element / Math.pow(10, currentdigits)) != 0) {
                currentdigits++;
            }
            digits = Math.max(digits, currentdigits);
        }
        return digits;*/
        int max = 0;

        for (int element : elements) {
            max = Math.max(max, element);
        }

        int digits = 0;
        while (max > 0) {
            max /= 10;
            digits++;
        }

        return digits;
    }

    public static void concatenate(List<Integer>[] buckets, int[] elements) {
        int i = 0;

        for (List<Integer> bucket : buckets) {
            for (int val : bucket) {
                elements[i++] = val;
            }
        }
    }

    public static void kSort(int[] elements, int decPlace) {
        @SuppressWarnings("unchecked")
        List<Integer>[] buckets = new List[DIGITS];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>(elements.length / DIGITS);
        }
        for (int element : elements) {
            buckets[key(element, decPlace)].add(element);
        }
        concatenate(buckets, elements);
    }

    public static void sort(int[] elements) {
        int decPlaces = getMaxDecimalPlaces(elements);
        for (int decPlace = 0; decPlace < decPlaces; decPlace++) {
            kSort(elements, decPlace);
        }
    }

    public static void main(String[] argc) {
        int[] arr = new int[5];
        Random random = new Random(400);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(0, 100);
        }
        RadixSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}