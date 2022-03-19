package ch.usi.ee.data;

public class Sorters {
    /**
     * Reverse the order of the elements in the float array
     *
     * @param source array to reverse
     * @param size   the size of the array
     * @return the reversed array
     */
    public static Comparable[] reverseArray(Comparable[] source, int size) {
        Comparable[] reversedFloats = new Comparable[size];
        for (int i = 0; i < size; i++) {
            reversedFloats[i] = source[size - i - 1];
        }

        return reversedFloats;
    }

    /**
     * Quick sort an array of type int, short, float, or String
     *
     * @param source array to sort
     * @param start  the start index
     * @param end    the end index
     * @return the sorted array
     */
    public static Object[] quickSort(Object[] source, int start, int end) {
        int i = start;
        int j = end;
        Object pivot = source[(start + end) / 2];

        while (i <= j) {
            while (compare(source[i], pivot) < 0) {
                i++;
            }

            while (compare(source[j], pivot) > 0) {
                j--;
            }

            if (i <= j) {
                Object temp = source[i];
                source[i] = source[j];
                source[j] = temp;
                i++;
                j--;
            }
        }

        if (start < j) {
            quickSort(source, start, j);
        }

        if (i < end) {
            quickSort(source, i, end);
        }

        return source;
    }

    /**
     * Compare int, short, float, or String
     * @param o1 the first object
     *            o2 the second object
     *            return -1 if o1 < o2/ */
    protected static int compare(Object o1, Object o2) {
        if (o1 instanceof Integer) {
            return ((Integer) o1).compareTo((Integer) o2);
        } else if (o1 instanceof Short) {
            return ((Short) o1).compareTo((Short) o2);
        } else if (o1 instanceof Float) {
            return ((Float) o1).compareTo((Float) o2);
        } else if (o1 instanceof String) {
            return ((String) o1).compareTo((String) o2);
        } else {
            return -1;
        }
    }

}