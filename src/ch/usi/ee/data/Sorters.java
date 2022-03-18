package ch.usi.ee.data;

public class Sorters {
    /**
     * Reverse the order of the elements in the int array
     *
     * @param source the array to reverse
     * @param size   the size of the array
     * @return the reversed array
     */
    public static int[] reverseArray(int[] source, int size) {
        int[] reversedIntegers = new int[size];
        for (int i = 0; i < size; i++) {
            reversedIntegers[i] = source[size - i - 1];
        }

        return reversedIntegers;
    }

    /**
     * Reverse the order of the elements in the short array
     *
     * @param source the array to reverse
     * @param size   the size of the array
     * @return the reversed array
     */
    public static short[] reverseArray(short[] source, int size) {
        short[] reversedShorts = new short[size];
        for (int i = 0; i < size; i++) {
            reversedShorts[i] = source[size - i - 1];
        }

        return reversedShorts;
    }

    /**
     * Reverse the order of the elements in the float array
     *
     * @param source the array to reverse
     * @param size   the size of the array
     * @return the reversed array
     */
    public static float[] reverseArray(float[] source, int size) {
        float[] reversedFloats = new float[size];
        for (int i = 0; i < size; i++) {
            reversedFloats[i] = source[size - i - 1];
        }

        return reversedFloats;
    }

    /**
     * Reverse the order of the elements in the string array
     *
     * @param source the array to reverse
     * @param size   the size of the array
     * @return the reversed array
     */
    public static String[] reverseArray(String[] source, int size) {
        String[] reversedStrings = new String[size];
        for (int i = 0; i < size; i++) {
            reversedStrings[i] = source[size - i - 1];
        }

        return reversedStrings;
    }

    /**
     * Quick sort an array of type int
     *
     * @param source the array to sort
     * @param start  the start index
     * @param end    the end index
     * @return the sorted array
     */
    public static int[] quickSort(int[] source, int start, int end) {
        int i = start;
        int j = end;
        int pivot = source[(start + end) / 2];

        while (i <= j) {
            while (source[i] < pivot) {
                i++;
            }

            while (source[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = source[i];
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
     * Quick sort an array of type short
     *
     * @param source the array to sort
     * @param start  the start index
     * @param end    the end index
     * @return the sorted array
     */
    public static short[] quickSort(short[] source, int start, int end) {
        int i = start;
        int j = end;
        short pivot = source[(start + end) / 2];

        while (i <= j) {
            while (source[i] < pivot) {
                i++;
            }

            while (source[j] > pivot) {
                j--;
            }

            if (i <= j) {
                short temp = source[i];
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
     * Quick sort an array of type float
     *
     * @param source the array to sort
     * @param start  the start index
     * @param end    the end index
     * @return the sorted array
     */
    public static float[] quickSort(float[] source, int start, int end) {
        int i = start;
        int j = end;
        float pivot = source[(start + end) / 2];

        while (i <= j) {
            while (source[i] < pivot) {
                i++;
            }

            while (source[j] > pivot) {
                j--;
            }

            if (i <= j) {
                float temp = source[i];
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
     * Quick sort an array of type int, short, float, or String
     *
     * @param source the array to sort
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
     * Quick sort an array of type String
     *
     * @param source the array to sort
     * @param start  the start index
     * @param end    the end index
     * @return the sorted array
     */
    protected static String[] quickSort(String[] source, int start, int end) {
        int i = start;
        int j = end;
        String pivot = source[(start + end) / 2];

        while (i <= j) {
            while (source[i].compareTo(pivot) < 0) {
                i++;
            }

            while (source[j].compareTo(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                String temp = source[i];
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