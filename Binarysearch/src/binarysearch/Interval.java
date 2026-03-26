package binarysearch;

import java.util.Objects;

public abstract class Interval {

    public abstract int getFrom();

    public abstract int getTo();

    public static Interval fromArrayIndices(int from, int to) {
        if (to < from) {
            return EmptyInterval.getEmptyInterval();
        } else if (to < 0 || from < 0) {
            return EmptyInterval.getEmptyInterval();
        } else {
            return new NonEmptyInterval(from, to);
        }
    }

    public static class NonEmptyInterval extends Interval {
        private final int from;
        private final int to;

        @Override
        public int getFrom() {
            return from;
        }

        @Override
        public int getTo() {
            return to;
        }

        public NonEmptyInterval(int from, int to) {
            if (to >= from) {
                this.from = from;
                this.to = to;
            } else {
                throw new IllegalArgumentException("Invalid interval boundary");
            }
        }

        @Override
        public String toString() {
            return "[" + from + "; " + to + "]";
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof NonEmptyInterval other) {
                return from == other.from && to == other.to;
            } else {
                return false;
            }
        }
    }

    public static final class EmptyInterval extends Interval {
        private static final EmptyInterval singleton = new EmptyInterval();

        private EmptyInterval() {
        }

        public static EmptyInterval getEmptyInterval() {
            return singleton;
        }

        @Override
        public int getFrom() {
            throw new UnsupportedOperationException("No lower boundary in empty interval");
        }

        @Override
        public int getTo() {
            throw new UnsupportedOperationException("No upper boundary in empty interval");
        }

        @Override
        public String toString() {
            return "[]";
        }
    }
}