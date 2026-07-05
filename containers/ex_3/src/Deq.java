class Deq<T> extends Queue<T> {
    public Deq(int capacity) {
        super(capacity);
    }

    public Deq() {
        super();
    }

    public Deq(Deq<T> other) {
        this.array = other.array.clone();
        this.size = other.size;
        this.head = other.head;
        this.tail = other.tail;
    }

    protected int backward(int index) {
        return --index >= 0 ? index : array.length - 1;
    }

    public void pushFront(T val) throws Exception {
        if (size == array.length)
            throw new Exception("Queue overflow");
        head = backward(head);
        array[head] = val;
        size++;
    }

    public void pushBack(T val) throws Exception {
        if (size == array.length)
            throw new Exception("Queue overflow");
        tail = forward(tail);
        array[tail] = val;
        size++;
    }

    public T popFront() throws Exception {
        if (empty())
            throw new Exception("Queue empty");
        T val = (T) array[head];
        head = forward(head);
        size--;
        return val;
    }

    public T popBack() throws Exception {
        if (empty())
            throw new Exception("Queue empty");
        T val = (T) array[tail];
        tail = backward(tail);
        size--;
        return val;
    }

    public T back() throws Exception {
        if (empty())
            throw new Exception("Queue empty");
        return (T) array[tail];
    }
}