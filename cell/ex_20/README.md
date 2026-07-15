# Задание 20. Выпуклая оболочка --- 1

## Формулировка задания

Модифицируйте код проекта «Выпуклая оболочка» так, чтобы:

1. В отдельном окне после ввода очередной точки визуализировалось актуальное
состояние выпуклой оболочки.
2. Все вводимые точки записывались в текстовый файл.
3. Вместо метода equal в классе R2Point использовался переопределенный метод equals
класса Object.

4. Вместо класса Deq.java использовался шаблон java.util.ArrayDeque<E>.

5. Посредством двойной буферизации был устранен эффект «мерцания» экрана при
перерисовки выпуклой оболочки.

```java
@Override
public void paint(Graphics g){
    super.paint(g);
    //g.clearRect(-width/2, height/2, width, height); //очистка экрана, если нет
    буферизации
    BufferedImage bufferedImage = new BufferedImage(width, height,
    BufferedImage.TYPE_INT_ARGB);
    Graphics g2 = bufferedImage.createGraphics(); //или Graphics2D
    //отрисовка выпуклой оболочки и осей координат в g2 (а не в g)
    //Graphics2D g2d = (Graphics2D)g;
    g.drawImage(bufferedImage, 0, 0, null); //или в g2d
}
```

6. Для модифицированной версии проекта постройте диаграмму классов.

## Отчет по заданию

### Список проделанных действий
