import java.util.ArrayDeque;

//Класс "Многоугольник", реализующий интерфейс фигуры.
class Polygon implements Figure{
    private double s, p;
    private ArrayDeque<R2Point> _container = new ArrayDeque<>();

    public Polygon(R2Point a, R2Point b, R2Point c){
        _container.addFirst(b);

        if (b.light(a, c)){
            _container.addFirst(a);
            _container.addLast(c);
        }
        else{
            _container.addFirst(c);
            _container.addLast(a);
        }

        p = R2Point.dist(a, b) + R2Point.dist(b, c)+ R2Point.dist(c, a);
        s = Math.abs(R2Point.area(a, b, c));
    }

    public double perimeter(){
        return p;
    }

    public double area(){
        return s;
    }

    private void grow(R2Point a, R2Point b, R2Point t){
        p -= R2Point.dist(a, b);
        s += Math.abs(R2Point.area(a, b, t));
    }

    public Figure add(R2Point t){
        int i;
        //Ищем освещенные ребра, просматривая их одно за другим.
        for(i = _container.size(); i>0 && !t.light(_container.peekLast(), _container.peekFirst()); i--)
            _container.addLast(_container.removeFirst());

        //УТВЕРЖДЕНИЕ:
        //либо ребро [back(), front()] освещено из t,
        //либо освещенных ребер нет совсем.
        if (i>0){
            R2Point x;
            grow(_container.peekLast(), _container.peekFirst(), t);

            //Удаляем все освещенные ребра из начала дека.
            for(x = _container.removeFirst(); t.light(x, _container.peekFirst()); x = _container.removeFirst())
                grow(x, _container.peekFirst(), t);
            _container.addFirst(x);

            //Удаляем все освещенные ребра из конца дека.
            for (x = _container.removeLast(); t.light(_container.peekLast(), x); x = _container.removeLast())
                grow(_container.peekLast(), x, t);
            _container.addLast(x);

            //Завершаем обработку добавляемой точки.
            p += R2Point.dist(_container.peekLast(), t) + R2Point.dist(t, _container.peekFirst());
            _container.addFirst(t);
        }

        return this;
    }
}