class Queue {
    protected final static int DEFSIZE = 16;
    protected int[] array;
    protected int size, head, tail;

    public Queue(int capacity) {
        array = new int[capacity];
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

    public void push(int val) throws Exception {
        if (++size > array.length)
            throw new Exception("Queue overflow");
        tail = forward(tail);
        array[tail] = val;
    }

    public int pop() throws Exception {
        int val = front();
        head = forward(head);
        size--;
        return val;
    }

    public int front() throws Exception {
        if (empty())
            throw new Exception("Queue empty");
        return array[head];
    }
}