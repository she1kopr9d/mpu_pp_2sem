class Deq extends Queue {
    public Deq(int capacity) {
        super(capacity);
    }

    public Deq() {
        super();
    }

    public Deq(Deq other) {
        this.array = other.array.clone();
        this.size = other.size;
        this.head = other.head;
        this.tail = other.tail;
    }

    protected int backward(int index) {
        return --index >= 0 ? index : array.length - 1;
    }

    public void pushFront(int val) throws Exception {
        if (size == array.length)
            throw new Exception("Queue overflow");
        head = backward(head);
        array[head] = val;
        size++;
    }

    public void pushBack(int val) throws Exception {
        if (size == array.length)
            throw new Exception("Queue overflow");
        tail = forward(tail);
        array[tail] = val;
        size++;
    }

    public int popFront() throws Exception {
        if (empty())
            throw new Exception("Queue empty");
        int val = array[head];
        head = forward(head);
        size--;
        return val;
    }

    public int popBack() throws Exception {
        if (empty())
            throw new Exception("Queue empty");
        int val = array[tail];
        tail = backward(tail);
        size--;
        return val;
    }

    public int back() throws Exception {
        if (empty())
            throw new Exception("Queue empty");
        return array[tail];
    }
}