/**
 * Experimentation & Evaluation SP2022
 * USI - Università della Svizzera italiana
 * Project: Java Bubble Sort Experiments
 *
 * Authors: Erick Garro Elizondo & Cindy Guerrero Toro
 */

package ch.usi.ee.bubble_sort;

public interface Sorter<T extends Comparable<T>> {
    public abstract void sort(final T[] items);
    //void sort(T[] items);
}