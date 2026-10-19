class Stack{
    private static final int DEFSIZE = 16;
    private int[] array;
    private int head;

    public Stack(int capacity) {
        array = new int[capacity];
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

    public void push(int val) throws Exception {
        array[head++] = val;
    }

    public int pop() throws Exception {
        return array[--head];
    }

    public int top() throws Exception {
        return array[head - 1];
    }
}