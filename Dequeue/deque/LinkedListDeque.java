package deque;


import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    //Defining my IntNode here
    private static class IntNode<T> {
        private IntNode<T> prev;
        private IntNode<T> next;
        private T item;

        //Initializing a new node here
        private IntNode(IntNode<T> prev, IntNode<T> next, T item) {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }
    }
    private int size;
    private IntNode<T> sentinel;

    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }


    public void addFirst(T item) {
        IntNode<T> newNode = new IntNode<>(sentinel, sentinel.next, item);
        sentinel.next = newNode;
        sentinel.next.next.prev = newNode;
        size++;
    }


    public void addLast(T item) {
        IntNode<T> newNode = new IntNode<>(sentinel.prev, sentinel, item);
        sentinel.prev = newNode;
        sentinel.prev.prev.next = newNode;
        size++;
    }


    public int size() {
        return size; //simply return the current size
    }


    public void printDeque() {
        IntNode<T> curr = sentinel.next; //setting a current value to be front
        while (curr != sentinel) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println();
    }


    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        } else {
            T points = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return points;
        }
    }


    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        } else {
            T points = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size--;
            return points;
        }
    }


    public T get(int index) {
        if (index < 0) {
            return null;
        } else {
            int i = 0;
            for (IntNode<T> p = sentinel.next; p.item != null; p = p.next) {
                if (i != index) {
                    i++;
                } else {
                    return p.item;
                }
            }
        }
        return null;
    }


    public T getRecursive(int index) {
        if (index < 0) {
            return null;
        }
        return getRecursiveHelper(index, 0, sentinel.next);
    }
    private T getRecursiveHelper(int index, int i, IntNode<T> p) {
        //When null return null
        if (p.item == null) {
            return null;
        }
        //Get the correct item
        if (i == index) {
            return p.item;
        }
        //Recursive call for the next
        return getRecursiveHelper(index, i + 1, p.next);
    }


    //prof's lecture: old school method
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        } else if (!(o instanceof Deque)) {
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
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int wizPos;
        public LinkedListDequeIterator() {
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
