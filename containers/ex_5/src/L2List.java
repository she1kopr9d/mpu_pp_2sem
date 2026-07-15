public class L2List implements IL2List {
    private static final int MAX_SIZE = 10;
    private final int[] data;
    private final int[] next;
    private final int[] prev;
    private final int HEAD = 0;
    private final int TAIL;
    private int free;
    private int cur;

    public L2List() {
        TAIL = MAX_SIZE + 1;
        data = new int[MAX_SIZE + 2];
        next = new int[MAX_SIZE + 2];
        prev = new int[MAX_SIZE + 2];
        next[HEAD] = TAIL;
        prev[TAIL] = HEAD;
        prev[HEAD] = -1;
        next[TAIL] = -1;
        free = 1;
        for (int i = 1; i < MAX_SIZE; i++) {
            next[i] = i + 1;
        }
        next[MAX_SIZE] = -1;
        cur = HEAD;
    }

    private int allocateNode() throws RuntimeException {
        if (free == -1) {
            throw new RuntimeException("List overflow");
        }
        int node = free;
        free = next[node];
        return node;
    }

    private void freeNode(int node) {
        next[node] = free;
        free = node;
    }

    @Override
    public boolean empty() {
        return next[HEAD] == TAIL;
    }

    @Override
    public void clear() {
        int node = next[HEAD];
        while (node != TAIL) {
            int nextNode = next[node];
            freeNode(node);
            node = nextNode;
        }
        next[HEAD] = TAIL;
        prev[TAIL] = HEAD;
        cur = HEAD;
    }

    @Override
    public void toFront() {
        cur = HEAD;
    }

    @Override
    public void toBack() {
        if (empty()) {
            cur = HEAD;
        } else {
            cur = prev[TAIL];
        }
    }

    @Override
    public boolean begin() {
        return cur == HEAD;
    }

    @Override
    public boolean end() {
        return next[cur] == TAIL;
    }

    @Override
    public void forward() throws Exception {
        if (end()) {
            throw new Exception("Cannot move forward: already at the end");
        }
        cur = next[cur];
    }

    @Override
    public void backward() throws Exception {
        if (begin()) {
            throw new Exception("Cannot move backward: already at the beginning");
        }
        cur = prev[cur];
    }

    @Override
    public int after() throws Exception {
        if (end()) {
            throw new Exception("No element after cursor");
        }
        return data[next[cur]];
    }

    @Override
    public int before() throws Exception {
        if (begin()) {
            throw new Exception("No element before cursor");
        }
        return data[cur];
    }

    @Override
    public void insertBack(int val) throws RuntimeException {
        int newNode = allocateNode();
        data[newNode] = val;
        int left = cur;
        int right = next[cur];
        next[newNode] = right;
        prev[newNode] = left;
        next[left] = newNode;
        prev[right] = newNode;
    }

    @Override
    public void insertFront(int val) throws RuntimeException {
        if (begin()) {
            int newNode = allocateNode();
            data[newNode] = val;
            int left = HEAD;
            int right = next[HEAD];
            next[newNode] = right;
            prev[newNode] = left;
            next[left] = newNode;
            prev[right] = newNode;
        } else {
            int newNode = allocateNode();
            data[newNode] = val;
            int left = prev[cur];
            int right = cur;
            next[newNode] = right;
            prev[newNode] = left;
            next[left] = newNode;
            prev[right] = newNode;
        }
    }

    @Override
    public int eraseBack() throws Exception {
        if (end()) {
            throw new Exception("No element to erase after cursor");
        }
        int nodeToRemove = next[cur];
        int right = next[nodeToRemove];
        next[cur] = right;
        prev[right] = cur;
        int value = data[nodeToRemove];
        freeNode(nodeToRemove);
        return value;
    }

    @Override
    public int eraseFront() throws Exception {
        if (begin()) {
            throw new Exception("No element to erase before cursor");
        }
        int nodeToRemove = prev[cur];
        int left = prev[nodeToRemove];
        next[left] = cur;
        prev[cur] = left;
        int value = data[nodeToRemove];
        freeNode(nodeToRemove);
        return value;
    }
}