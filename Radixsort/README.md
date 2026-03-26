# Radix Sort (Java)

This project contains two implementations of **Radix Sort** in Java:

* **Decimal Radix Sort (base 10)**
* **Binary Radix Sort (base 2)**

Radix Sort is a **non-comparative sorting algorithm** that processes numbers digit by digit.
It is especially efficient when sorting large sets of integers.

---

## Structure

The implementation consists of three classes:

**RadixSort**

* decimal radix sort implementation
* distributes elements into 10 buckets based on decimal digits

**BinaryRadixSort**

* radix sort using binary representation
* processes elements bit by bit (32 bits for integers)

**BinaryBucket**

* helper structure used by the binary radix sort
* stores elements while splitting them based on bit values

---

## Algorithm Idea

Radix Sort processes numbers from the **least significant digit (LSD)** to the **most significant digit (MSD)**.

### Decimal Version

For each decimal position:

1. Extract the digit using the key function
2. Distribute elements into 10 buckets (0–9)
3. Concatenate the buckets

Handles only **positive integers**
### Binary Version

For each bit position:

1. Split numbers based on the current bit (0 or 1)
2. Move elements into two partitions
3. Repeat for all 32 bits of the integer

The binary version also correctly handles **negative integers**.

---

## Performance Test

The implementation includes a simple performance comparison between binary and decimal radix sort using randomly generated integers.

```

Decimal: 2373.5213ms
Binary: 1051.5413ms
```

(The exact runtime depends on hardware and dataset size.)
