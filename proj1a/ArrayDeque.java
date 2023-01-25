public class ArrayDeque<T> {
    private int size;
    private int length;
    private T[] items;
    private int p1;
    private int p2;
    private double R;

    /*invariants
    1. p1 + 1 = index of first item;
    2. p2 - 1 = index of last item;
    3. addfirst p1 -1, special case:
      if p1 == 0, p1 = length - 1;
    4. addlast p2 + 1, special case:
     if p2 == length - 1, p2 == 0; */
    // 超出的重归于零 - modulus
    /*
    plus: p2 % length
    5. removeFirst p1 + 1
    6. removeLast p2 - 1
    7. resize: different design
    1) initial: p1 + 1 = p2,  when size = length,
     p1 + 1 = p2
     */
    // add： when size = length , grow;
    // remove: when R < 0.25 && length >= 16, shrink;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        length = items.length;
        R = 0.0;
        p1 = 3;
        p2 = 4;
    }


     //should R be instance variable or method ？
    private double usageRatio (int s, int l) {
        R = (double) s / l;
        if (l >= 16 && R < 0.25) {
            this.shrink();
        }
        return R;
    }

    private void shrink() {
        T[] newItems = (T[]) new Object[length / 2];
        int ptr1 = ArrayDeque.plusOne(p1,length);
        int ptr2 = 0;
        while (ptr2 < size) {
            newItems[ptr2] = items[ptr1];
            ptr1 = ArrayDeque.plusOne(ptr1,length);
            ptr2++;
        }
        items = newItems;
        length /= 2;
        p1 = ArrayDeque.minusOne(0, length);
        p2 = size;

    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int index = ArrayDeque.plusOne(p1,length);
        T ans = items[index];
        p1 = index;
        items[p1] = null;
        size--;
        R = this.usageRatio(size,length);
        return ans;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int index = ArrayDeque.minusOne(p2,length);
        T ans = items[index];
        p2 = index;
        items[p2] = null;
        size--;
        R = this.usageRatio(size,length);
        return ans;
    }

    private static int plusOne(int p, int modulo) {
        p++;
        p %= modulo;
        return p;
    }

    private static int minusOne(int p, int l) {
        if (p == 0) {
            p = l - 1;
        } else {
            p--;
        }
        return p;
    }

    private void grow() {
        T[] newItems = (T[]) new Object[length * 2];
        int ptr1 = ArrayDeque.plusOne(p1,length);
        int ptr2 = 0;
        while (ptr2 < size) {
            newItems[ptr2] = items[ptr1];
            ptr1 = ArrayDeque.plusOne(ptr1,length);
            ptr2++;
        }
        items = newItems;
        length *= 2;
        p1 = ArrayDeque.minusOne(0, length);
        p2 = size;
    }

    public void addFirst(T item) {
        if (size == length) {
            grow();
        }
        items[p1] = item;
        p1 = ArrayDeque.minusOne(p1, length);
        size++;
    }

    public void addLast(T item) {
        if (size == length) {
            grow();
        }
        items[p2] = item;
        p2 = ArrayDeque.plusOne(p2,length);
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
        int i = 0;
        int j = p1;
        while (i < size) {
            j = ArrayDeque.plusOne(j, length);
            System.out.print(items[j] + " ");
            i++;
        }
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int i = 0;
        int j = p1;
        while (i <= index) {
            j = ArrayDeque.plusOne(j, length);
            i++;
        }
        return items[j];
    }



/*
    public static void main(String[] args) {
        ArrayDeque<Integer> test = new ArrayDeque<>();

        int j = 0;
        while (j < 400) {
            test.addFirst(j);
            j++;
        }
        int i = 0;
        while (i < 400) {
            test.removeLast();
            i++;
        }
        int k = 0;
        while (k < 18) {
            test.addLast(k);
            k++;
        }
        test.printDeque();
        System.out.println(test.size());
        System.out.println(test.get(0));
        int a = test.removeLast();
        System.out.println(a);
    }

 */


}
