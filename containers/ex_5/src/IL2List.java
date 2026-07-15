public interface IL2List {
    boolean empty();
    void clear();
    void toFront();
    void toBack();
    boolean begin();
    boolean end();
    void forward() throws Exception;
    void backward() throws Exception;
    int after() throws Exception;
    int before() throws Exception;
    void insertBack(int val);
    void insertFront(int val);
    int eraseBack() throws Exception;
    int eraseFront() throws Exception;
}
