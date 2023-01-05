package deque;

//Deque interface just delete the public syntax in front each method
public interface Deque<T> {
    void addFirst(T item);
    void addLast(T item);
    default boolean isEmpty() {
        return size() == 0;
    }
    int size();
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);

}
