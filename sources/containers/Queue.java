class Queue {
    private final static int DEFSIZE = 16;
    private int[] array;
    private int size, head, tail;

    public Queue(int capacity) {
        array = new int[capacity];
        clear();
    }

    public Queue(){
        this(DEFSIZE);
    }

    public boolean empty() {
        return size == 0;
    }

    public void clear() {
        size = head = 0;
        tail = array.length - 1;
    }

    private int forward(int index) {
        return ++index < array.length ? index : 0;
    }

    public void push(int val) throws Exception {
        if(++size > array.length)
            throw new Exception();
        array[tail = forward(tail)] = val;
    }

    public int pop() throws Exception {
        int val = front();
        head = forward(head);
        size -= 1;
        return val;
    }

    public int front() throws Exception {
        if(empty())
            throw new Exception();
        return array[head];
    }
}