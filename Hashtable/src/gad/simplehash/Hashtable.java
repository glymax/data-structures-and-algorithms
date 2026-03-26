package gad.simplehash;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

public class Hashtable<K, V> {

    private final List<Pair<K, V>>[] table;
    private final int[] param;

    @SuppressWarnings("unchecked")
    public Hashtable(int minSize, int[] a) {
        table = (List<Pair<K, V>>[]) new List[getNextPowerOfTwo(minSize)];
        Arrays.setAll(table, e -> new ArrayList<Pair<K, V>>());
        param = a;
    }

    public List<Pair<K, V>>[] getTable() {
        return table;
    }

    public static int getNextPowerOfTwo(int i) {
        int n = 1;
        while (n < i) {
            n <<= 1;
        }
        return n;
    }

    public static int fastModulo(int i, int divisor) {
        return i & (divisor - 1);
    }

    private byte[] bytes(K k) {
        return k.toString().getBytes(StandardCharsets.UTF_8);
    }

    public int h(K k) {
        byte[] x = bytes(k);
        int sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum += x[i] * param[i % param.length];
        }
        return fastModulo(sum, table.length);
    }

    public void insert(K k, V v) {
        table[h(k)].add(new Pair<>(k, v));
    }

    public boolean remove(K k) {
        List<Pair<K, V>> list = table[h(k)];
        int oldLength = list.size();
        list.removeIf(p -> p.one().equals(k));
        return list.size() < oldLength;
    }

    public Optional<V> find(K k) {
        return table[h(k)].stream()
                .filter(elem -> elem.one().equals(k))
                .map(Pair::two)
                .findFirst();
    }

    public List<V> findAll(K k) {
        return table[h(k)].stream()
                .filter(elem -> elem.one().equals(k))
                .map(Pair::two)
                .toList();
    }

    public Stream<Pair<K, V>> stream() {
        return Stream.of(table).filter(Objects::nonNull).flatMap(List::stream);
    }

    public Stream<K> keys() {
        return stream().map(Pair::one).distinct();
    }

    public Stream<V> values() {
        return stream().map(Pair::two);
    }

    public static void main(String... argc) {
        Hashtable<String, Integer> hashtable = new Hashtable<>(5, new int[]{1, 2, 3, 4, 5});
        hashtable.insert("a", 0);
        hashtable.insert("b", 1);
        hashtable.insert("a", 3);
        hashtable.insert("b", 1);
        hashtable.insert("c", 0);
        hashtable.insert("c", 1);
        hashtable.insert("ab", 1);
        hashtable.insert("ba", 1);
        hashtable.insert("ac", 1);
        hashtable.insert("ca", 1);
        hashtable.insert("aa", 1);
        hashtable.insert("bb", 1);
        hashtable.insert("cc", 1);
        hashtable.remove("a");
        System.out.println(hashtable.remove("a"));
        System.out.println(hashtable.remove("b"));
        System.out.println(hashtable.remove("q"));
        System.out.println(hashtable);
    }
}