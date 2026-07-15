public interface IEx<T> {
    //Получить номер элемента.
    public int find(Node<T> node);
    //Вставка элемента.
    public void insert(Node<T> node, int location);
    //Удаление элемента.
    public void remove(int location);
}
