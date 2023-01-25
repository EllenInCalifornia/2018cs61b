public class ArrayDeque<T> {
    /**invariant front + 1 = index of first item;
     * last - 1 = index of last item;
     * when size = length,
     * front + 1 = last && items[front] = lastitem;
     * items[last] = firstitem;
     * addfirst (front-1); addlast (last+1);
     *
     */
    private T[] items;
    private int size;
    private int front;
    public int last;
    private int index;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        front = 3;
        last = 4;
        size = 0;
    }


    public void reSize() {
        T[] newItems = (T[]) new Object[items.length*2];
        System.arraycopy(items,0,newItems,0,front + 1);
        System.arraycopy(items,front + 1,newItems,
                items.length + last, items.length - last);
        front = items.length + last -1;
        items = newItems;
    }

    public void addFirst(T i) {
        if (size == items.length) reSize();
        items[front] = i;
        if (front  == 0 ) {
            front = items.length - 1;
        }
        else front--;
        size++;

    }

    public void addLast(T item) {
        if (size == items.length) reSize();
        items[last] = item;
        if (last == items.length -1) last = 0;
        else last++;
        size++;
    }

    public boolean isEmpty() {
        if (size == 0) return true;
        return false;
    }

    public int size() {
        return size;

    }

    public void printDeque() {
        int i = 0;
        int j = front;
        while (i < size) {
            if (j == items.length -1) j = -1;
            System.out.print(items[j+1] + " ");
            j++;
            i++;
        }

    }

    public T removeFirst() {
        T ans;
        if (size == 0) return null;
        if (front == items.length -1) {
            ans = items[0];
            front = 0;
        }
        else{
            ans = items[front + 1];
            front++;
        }
        items[front] = null;
        size--;
        return ans;
    }

    public T removeLast() {
        if (size == 0) return null;
        T ans;
        if (last == 0) {
            ans = items[items.length -1];
            last =items.length - 1;
        }
        else {
            ans = items[last - 1];
            last--;
        }
        items[last] = null;
        size--;
        return ans;

    }

    
    public T get(int index) {
        if (front + 1 + index < items.length)
            return items[front + 1 + index];
        else return items[front + 1 + index - items.length];
    }

}
