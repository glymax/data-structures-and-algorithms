# Hashtable

A Java implementation of a hash table with chaining to handle collisions. Supports storing multiple values for a single key.

## Classes

- `Hashtable<K,V>` – main hash table implementation.
- `Pair<K,V>` – helper class representing a key-value pair.

---

## Main Methods

### `insert(K k, V v)`
Adds a key-value pair to the table.  
Multiple values can be stored for the same key.

### `remove(K k)`
Removes all entries with the specified key.  
Returns `true` if any entries were removed.

### `find(K k)`
Returns the first value for a key as `Optional<V>`.  
Returns `Optional.empty()` if the key does not exist.

### `findAll(K k)`
Returns a list of all values for a given key.

### `stream()`
Returns a `Stream` of all key-value pairs in the table.

### `keys()`
Returns a stream of all unique keys.

### `values()`
Returns a stream of all values.

---

## Implementation Details
The internal array table is sized to the next power of two for efficient bitwise modulo operations.
Collisions are handled using chaining (a List<Pair<K,V>> for each bucket).
The hash function uses the UTF-8 bytes of the key and a param array to improve distribution.
The table supports multiple values per key.