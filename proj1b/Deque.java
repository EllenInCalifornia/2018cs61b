public interface Deque<T> {

    public void addFirst(T ff);
    public void addLast(T ll);

    public boolean isEmpty();
    public int size();
    public void printDeque();
    public T removeFirst();
    public T removeLast();
    public T get(int index);

}
