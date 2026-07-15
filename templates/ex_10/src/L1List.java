import java.util.Objects;

public class L1List<T> implements IEx<T>{
    private Node<T> first, last; //Ссылки на первый и последний узлы.
    private int size;
    public L1List(){
        first = last = null;
        size = 0;
    }
    //Добавить элемент в конец списка.
    public void append(Node<T> node){
        node.next = null;
        if(first == null) //Первый элемент.
            first = node;
        if(last != null)
            last.next = node;
        last = node;
        size++;
    }

    public int size() {
        return size;
    }

    @Override
    public int find(Node<T> node) {
        if (first == null || node == null) return -1;
        Node<T> current = first;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.value, node.value)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public Node<T> get(int location) {
        if (first == null || location < 0 || location >= size) {
            return null;
        }
        Node<T> current = first;
        for (int i = 0; i < location; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public void insert(Node<T> node, int location) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }
        if (location < 0 || location > size) {
            throw new IndexOutOfBoundsException("Invalid location: " + location);
        }
        if (location == 0) {
            node.next = first;
            first = node;
            if (last == null) {
                last = node;
            }
        } else if (location == size) {
            last.next = node;
            last = node;
            node.next = null;
        } else {
            Node<T> prev = get(location - 1);
            node.next = prev.next;
            prev.next = node;
        }
        size++;
    }

    @Override
    public void remove(int location){
        if (size == 0) {
            throw new IllegalStateException("Cannot remove from empty list");
        }
        if (location < 0 || location >= size) {
            throw new IndexOutOfBoundsException("Invalid location: " + location);
        }
        if (location == 0) {
            first = first.next;
            if (size == 1) {
                last = null;
            }
        } else {
            Node<T> prev = get(location - 1);
            prev.next = prev.next.next;
            if (location == size - 1) {
                last = prev;
            }
        }
        size--;
    }
}