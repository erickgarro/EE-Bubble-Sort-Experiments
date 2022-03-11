package ch.usi.ee.BubbleSort;

public interface Sorter<T extends Comparable<T>> {
    public abstract void sort(final T[] items);
    //void sort(T[] items);
}