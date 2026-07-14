public class L1List {
    private final static int DEFSIZE = 16;
    private int[] array;
    private int[] next;
    private int nilList;
    private int nilFree;
    private int before;
    private int after;

    public L1List(int capacity) {
        array = new int[capacity];
        next = new int[capacity + 2];
        nilList = capacity;
        nilFree = capacity + 1;
        link(nilList, nilList);
        link(nilFree, 0);
        for (int i = 0; i < capacity - 1; i++)
        link(i, i + 1);
        link(capacity - 1, nilFree);
        before = after = nilList;
    }

    public L1List(){
        this(DEFSIZE);
    }

    private void link(int first, int second) {
        next[first] = second;
    }

    private int mallocIndex() {
        int index = next[nilFree];
        link(nilFree, next[index]);
        return index;
    }

    private void freeIndex(int index) {
        link(index, next[nilFree]);
        link(nilFree, index);
    }

    public boolean empty() {
        return next[nilList] == nilList;
    }

    public void clear() {
        try {
            toFront();
            while (true)
                erase();
        } catch(Exception e) {;}
    }

    public void toFront() {
        before = nilList;
        after = next[nilList];
    }

    public boolean end() {
        return after == nilList;
    }

    public void forward() throws Exception {
        if(after == nilList)
            throw new Exception();
        before = after;
        after = next[after];
    }

    public int after() throws Exception {
        return array[after];
    }

    public void insert(int val) throws Exception {
        int index = mallocIndex();
        link(before, index);
        link(index, after);
        after = index;
        array[index] = val;
    }

    public int erase() throws Exception {
        int val = array[after];
        int index = after;
        after = next[index];
        link(before, after);
        freeIndex(index);
        return val;
    }
}