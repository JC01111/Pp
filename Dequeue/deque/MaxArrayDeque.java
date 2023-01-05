package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max(Comparator<T> c) {
        //Start from 0th index
        T max = get(0);
        for (int i = 1; i < size(); i++) {
            T curr = get(i);
            if (c.compare(max, curr) < 0) {
                max = curr;
            }
        }
        return max;
    }

    public T max() {
        return max(comparator);
    }

}
