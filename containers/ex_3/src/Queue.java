class Queue<T> {
    protected final static int DEFSIZE = 16;
    protected Object[] array;
    protected int size, head, tail;

    public Queue(int capacity) {
        array = new Object[capacity];
        clear();
    }

    public Queue() {
        this(DEFSIZE);
    }

    public boolean empty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        head = 0;
        tail = array.length - 1;
    }

    protected int forward(int index) {
        return ++index < array.length ? index : 0;
    }

    public void push(T val) throws Exception {
        if (++size > array.length)
            throw new Exception("Queue overflow");
        tail = forward(tail);
        array[tail] = val;
    }

    public T pop() throws Exception {
        T val = front();
        head = forward(head);
        size--;
        return val;
    }

    public T front() throws Exception {
        if (empty())
            throw new Exception("Queue empty");
        return (T) array[head];
    }
}