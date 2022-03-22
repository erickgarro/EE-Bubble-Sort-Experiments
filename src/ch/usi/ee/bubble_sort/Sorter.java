package ch.usi.ee.bubble_sort;

public interface Sorter<T extends Comparable<T>> {
    public abstract void sort(final T[] items);
    //void sort(T[] items);
}