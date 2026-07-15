# Задание 10. Модификация шаблона класса

## Формулировка задания

```java
//Node.java
public class Node<T>{
    public T value;
    public Node<T> next;
    public Node(T value){
        this.value = value;
        next = null;
    }
}
```

```java
//L1List.java
public class L1List<T>{
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
}
```

Объект класса Node содержит ссылку на объект класса Node:

Шаблон нового класса L1List (на основе ссылочной адресации) описывает односвязный
список, дополните его перечисленными методами (и др. на свое усмотрение):
//Получить номер элемента.
public int find(Node node);
//Вставка элемента.
public void insert(Node node, int location);
//Удаление элемента.
public void remove(int location);
1 Работа над решением должна сопровождаться полноценным ведением локального
репозитория и дополняться покрывающими тестами.

2
Создайте список из пяти элементов, удалите третий элемент и последовательно (с первого)
выведите оставшиеся.

## Отчет по заданию

### Список проделанных действий
