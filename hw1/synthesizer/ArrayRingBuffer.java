//  Make sure to make this class a part
//  of the synthesizer package
package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int cap) {
        //  Create new array with capacity elements.
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[cap];
        capacity = cap;

        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        //  Enqueue the item. Don't forget to increase fillCount and update last.
        if (fillCount == capacity) {
            throw new RuntimeException("Ring Buffer Overflow");
        } else {
            rb[last] = x;
            last = (last + 1) % capacity;
            fillCount++;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new BqIterator<T>();
    }

    // implement iterator interface;
    private class BqIterator<T> implements Iterator<T> {
        private int ptr;
        BqIterator() {
            ptr = 0;
        }

        @Override
        public boolean hasNext() {
            return ptr < fillCount;
        }
        public T next() {
           //注意这个generic syntax
            T returnValue = (T) rb[ptr];
            ptr++;
            return returnValue;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        //  Dequeue the first item.
        //  Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            T ans = rb[first];
            rb[first] = null;
            first = (first + 1) % capacity;
            fillCount--;
            return ans;
        }
    }


    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        //  Return the first item. None
        //  of your instance variables should change.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            return rb[first];
        }


    }

    // When you get to part 5, implement the needed code to support iteration.
}
