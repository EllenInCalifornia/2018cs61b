package synthesizer;

import java.util.Iterator;

/*
不明白
Note that isEmpty, isFull, peek, dequeue, enqueue, are
inherited from BoundedQueue, so you should not  declare
 these explicitly in your AbstractBoundedQueue.java file
 */
/*
The purpose of AbstractBoundedQueue will be to simply provide
a protected fillCount and capacity variable that all subclasses
will inherit, as well as so called “getter” methods capacity() and
 fillCount() that return capacity and fillCount , respectively.
 */
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    /*
    protected means that only subclasses of AbstractBoundedQueue
    and classes in the same package as AbstractBoundedQueue can
    access this variable.
     */
    protected int fillCount;
    protected int capacity;
    @Override
    public int capacity() {
        return capacity;
    }
    @Override
    public int fillCount(){
        return fillCount;
    }
    public abstract T peek();
    public abstract T dequeue();
    public abstract void enqueue(T x);
}
