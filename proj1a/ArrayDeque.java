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
    private int last;
    private double R;
    private int length;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        front = 3;
        last = 4;
        size = 0;
        length = items.length;
        R = 0.0;
    }
    private void updateR() {
        size = this.size;
        R = ((double) size) / length;
        while (length >= 16 && R < 0.25) {
            memoryManage();
        }
    }


    private void reSize() {
        T[] newItems = (T[]) new Object[length * 2];
        System.arraycopy(items, 0, newItems,
                0, front + 1);
        if (front + 1 < length) {
            System.arraycopy(items, front + 1, newItems,
                    length + last, length - last);
            front = length + last - 1;
        } else {
            last = front + 1;
            front = length * 2 - 1;
        }

        items = newItems;
        length = items.length;
        this.updateR();
    }

    private void memoryManage() {
        T[] smallItems = (T[]) new Object[length / 2];
        if (front < last ){
            System.arraycopy(items, front, smallItems,
                    smallItems.length / 4, size);
            items = smallItems;
            length = items.length;
            front = length / 4;
            last = front + size;
         }
        else {
            System.arraycopy(items, 0, smallItems,
                    0, last );
            System.arraycopy(items, front + 1, smallItems,
                    front + 1 - length / 2, length - front - 1);
            items = smallItems;
            length = items.length;
            front -= length;
        }

        this.updateR();
    }

    public void addFirst(T i) {
        if (size == length) {
            reSize();
        }
        items[front] = i;
        if (front  == 0) {
            front = length - 1;
        } else {
            front--;
        }
        size++;

    }

    public void addLast(T item) {
        if (size == length) {
            reSize();
        }
        items[last] = item;
        if (last == length -1) {
            last = 0;
        } else {
            last++;
        }
        size++;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;

    }

    public void printDeque() {
        int counter = 0;
        int j = front;
        while (counter < size) {
            if (j == length - 1) {
                j = -1;
            }
            System.out.print(items[j + 1] + " ");
            j++;
            counter++;
        }

    }

    public T removeFirst() {
        T ans;
        if (size == 0) {
            return null;
        }
        if (front == length - 1) {
            ans = items[0];
            front = 0;
        } else {
            ans = items[front + 1];
            front++;
        }
        items[front] = null;
        size--;
        this.updateR();
        return ans;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T lastitem;
        if (last == 0) {
            lastitem = items[length - 1];
            last = length - 1;
        } else {
            lastitem = items[last - 1];
            last--;
        }
        items[last] = null;
        size--;
        this.updateR();
        return lastitem;

    }

    public T get(int index) {
        if (front + 1 + index < length) {
            return items[front + 1 + index];
        } else {
            return items[front + 1 + index - length];
        }
    }

//    public static void main(String[] args) {
//        ArrayDeque<Integer> Test = new ArrayDeque<>();
//        Test.addLast(0);
//        Test.addFirst(1);
//        Test.size();
//        Test.addLast(3);
//        Test.size();
//        Test.addLast(5);
//        Test.addFirst(6);
//        Test.addLast(7);
//        Test.addFirst(8);
//        Test.addFirst(9);
//        Test.addFirst(10);
//        System.out.println(Test.size);
//        for (int i = 0; i < 30; i++) {
//            Test.addFirst(i);
//        }
//        for (int j = 0; j < 40; j++) {
//            Test.removeFirst();
//        }
//    }


}
