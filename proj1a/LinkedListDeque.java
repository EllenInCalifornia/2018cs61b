public class LinkedListDeque<T> {
    private TNode sentinel;
    private int size;
    private T i;

    private class TNode {
        private T item;
        private TNode next;
        private TNode pre;

        public TNode(T i, TNode p, TNode n) {
            item = i;
            pre = p;
            next = n;
        }
    }
    public LinkedListDeque() {
        //new 先instantiate，此时sentinel都是null；
        sentinel = new TNode(i, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }


    public void addFirst(T ff) {
        TNode first = new TNode(ff, sentinel, sentinel.next);

        sentinel.next.pre = first;
        sentinel.next = first;
        size++;
    }

    public void addLast(T ll) {
        TNode last = new TNode(ll, sentinel.pre, sentinel);
        sentinel.pre.next = last;
        sentinel.pre = last;
        size++;
    }
    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }

    //  Returns the number of items in the deque
    public int size() {
        return size;
    }
    //Prints the items in the deque from first to last
    public void printDeque() {
        TNode ptr = sentinel.next;
        while (ptr != sentinel) {
            // separated by a space
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }
    /*Removes and returns the item at the front of the deque.
     If no such item exists, returns null.
     */
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        TNode first = sentinel.next;
        sentinel.next = first.next;
        first.next.pre = sentinel;
        size--;
        return first.item;

    }

    public T removeLast() {
        if (sentinel.pre == sentinel) {
            return null;
        }
        TNode last = sentinel.pre;
        last.pre.next = sentinel;
        sentinel.pre = last.pre;
        size--;
        return last.item;

    }
    //iteration
    public T get(int index) {
        int x = 0;
        TNode ptr = sentinel.next;
        while (x < index && ptr != sentinel) {
            ptr = ptr.next;
            x++;
        }
        if (ptr == sentinel) {
            return null;
        }
        return ptr.item;
    }

    private T getRecursiveHelper(TNode t, int y) {
        if (t == sentinel) {
            return null;
        }
        if (y == 0) {
            return t.item;
        }
        return getRecursiveHelper(t.next, y - 1);

    }

    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }


}
