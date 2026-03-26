package binarysearch;

import binarysearch.Interval.NonEmptyInterval;

public final class BinSea {

    private BinSea() {
    }

    public static int search(int[] sortedData, int value) {
        int start = 0;
        int end = sortedData.length - 1;
        int mid = 0;
        while (start <= end) {
            mid = start + ((end - start) / 2);
            if (sortedData[mid] == value) {
                break;
            } else {
                if (sortedData[mid] > value) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return mid;
    }

    public static int search(int[] sortedData, int value, boolean lowerBound) {
        int arrayLengthOriginal = sortedData.length;
        int index = search(sortedData, value);
        if (lowerBound) { // UntereGrenze
            if (sortedData[index] == value) {
                while (index != 0 && sortedData[index - 1] == value) {
                    index--;
                }
            } else {
                if (sortedData[index] < value) {
                    if (index == arrayLengthOriginal - 1) {
                        index = -1;
                    } else {
                        index++;
                    }
                }
            }
        } else { //Obergrenze
            if (sortedData[index] == value) {
                while (index != arrayLengthOriginal - 1 && sortedData[index + 1] == value) {
                    index++;
                }
            } else {
                if (sortedData[index] > value) {
                    if (index == 0) {
                        index = -1;
                    } else {
                        index--;
                    }
                }
            }
        }
        return index;
    }

    public static Interval search(int[] sortedData, NonEmptyInterval valueRange) {
        int startindex = valueRange.getFrom();
        int endindex = valueRange.getTo();
        startindex = search(sortedData, startindex, true);
        if (startindex == -1) {
            return Interval.EmptyInterval.getEmptyInterval();
        }
        endindex = search(sortedData, endindex, false);
        return Interval.fromArrayIndices(startindex, endindex);
    }

    public static void main(String[] args) {
        int[] array1 = new int[]{1, 3};
        System.out.println(search(array1, 3));
        int[] array = new int[]{2, 7, 7, 42, 69, 1337, 2000, 9001};

        System.out.println(search(array, 7));
        System.out.println(search(array, 100));

        System.out.println(search(array1, 4, true));
        System.out.println(search(array, 10000, true));

        System.out.println(search(array, new NonEmptyInterval(7, 1500)));
        System.out.println(search(array, new NonEmptyInterval(9002, 10000)));
    }
}