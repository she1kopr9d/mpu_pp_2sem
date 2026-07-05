class Stack<T> {
    private static final int DEFSIZE = 16;
    private Object[] array;
    private int head;

    public Stack(int capacity) {
        array = new Object[capacity];
        head = 0;
    }

    public Stack() {
        this(DEFSIZE);
    }

    public boolean empty() {
        return head == 0;
    }

    public void clear() {
        head = 0;
    }

    public void push(T val) throws Exception {
        if (head == array.length) {
            throw new Exception("Stack overflow");
        }
        array[head++] = val;
    }

    public T pop() throws Exception {
        if (head == 0) {
            throw new Exception("Stack underflow");
        }
        return (T) array[--head];
    }

    public T top() throws Exception {
        if (head == 0) {
            throw new Exception("Stack underflow");
        }
        return (T) array[head - 1];
    }
}