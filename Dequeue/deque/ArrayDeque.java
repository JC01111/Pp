package deque;


import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int FOUR = 4;
    private static final int EIGHT = 8;

    public ArrayDeque() {
        items = (T[]) new Object[EIGHT];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    //TA Crystal Wang
    private void resize(int capacity) {
        T[] arr = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            int index = resizeHelper(i);
            //Update the array with previous elements
            arr[capacity / FOUR + i] = items[index];
        }
        //copy into new array
        items = arr;
        //update nextFirst and nextLast
        nextFirst = capacity / FOUR - 1;
        nextLast = nextFirst + size + 1;
    }

    //TA Crystal Wang
    private int resizeHelper(int ind) {
        if (nextFirst + 1 + ind >= items.length) {
            return nextFirst + 1 + ind - items.length;
        }
        return nextFirst + 1 + ind;
    }

    public void addFirst(T item) {
        if (size == items.length - 1) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length; //TA Shirley Chen
        size++;
    }

    public void addLast(T item) {
        if (size == items.length - 1) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1 + items.length) % items.length;
        size++;
    }


    public T get(int i) {
        return items[resizeHelper(i)];
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        //When we should resize down
        if ((size < items.length / FOUR) && (size > EIGHT)) {
            resize(items.length / 2);
        }
        //Get the current element
        T item = get(0);
        //Set the previous element to be null
        items[resizeHelper(0)] = null;
        size--;
        nextFirst = resizeHelper(0);
        return item;
    }


    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if ((size < items.length / FOUR) && (size > EIGHT)) {
            resize(items.length / 2);
        }
        //Get the current element
        T item = get(size - 1);
        int rmLast = resizeHelper(size - 1);
        //set the current element to be null
        items[rmLast] = null;
        size--;
        nextLast = rmLast;
        return item;
    }


    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }


    //Same method as LLD
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        } else if (!(o instanceof Deque)) { //Deque shared by AD and LLD
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (other.size() != this.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!(other.get(i).equals(this.get(i)))) {
                return false;
            }
        }
        return true;
    }


    //Below are from Prof's lecture
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;
        public ArrayDequeIterator() {
            wizPos = 0;
        }
        public boolean hasNext() {
            return wizPos < size;
        }
        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }


}
