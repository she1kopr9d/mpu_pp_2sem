# Задание 11. Использование шаблона класса

## Формулировка задания

Используя реализацию L1List из Задания 10, выполните Задание 4.

## Отчет по заданию

### Список проделанных действий

- Адаптировал код [Задания 4](../../containers/ex_4/) под реализацию L1List [Задания 10](../ex_10/).

### Вывод программы (Идентичен выводу программы 4)

```bash
--- Тест 1 ---
Пример ввода:
Run Mozilla Firefox
Run IntelliJ IDEA
Alt Tab +
Run UMLet
Alt Tab Tab + +
Alt Tab Tab Tab + + +
Alt Delete +

Вывод:
Mozilla Firefox
IntelliJ IDEA
Mozilla Firefox
UMLet
IntelliJ IDEA
IntelliJ IDEA
--------------
--- Тест 2 ---
Пример ввода:
Run A

Вывод:
A
--------------
--- Тест 3 ---
Пример ввода:
Run A
Run B
Alt Tab +

Вывод:
A
B
A
--------------
--- Тест 4 ---
Пример ввода:
Run A
Run B
Run C
Alt Tab Tab + +

Вывод:
A
B
C
A
--------------
--- Тест 5 ---
Пример ввода:
Run A
Run B
Run A

Вывод:
A
B
A
--------------
--- Тест 6 ---
Пример ввода:
Run A
Run B
Alt Delete +

Вывод:
A
B
--------------
--- Тест 7 ---
Пример ввода:
Run A
Run B
Alt Tab +
Alt Delete +

Вывод:
A
B
A
--------------
--- Тест 8 ---
Пример ввода:
Run A
Run B
Run C
Alt Tab Tab Tab Tab Tab + + + + +

Вывод:
A
B
C
A
--------------
--- Тест 9 ---
Пример ввода:
Run A
Run B
Run C
Alt Tab Tab Tab + + +

Вывод:
A
B
C
C
--------------
--- Тест 10 ---
Пример ввода:
Run A
Run B
Alt Delete +
Alt Delete +
Alt Delete +

Вывод:
A
B
---------------
--- Тест 11 ---
Пример ввода:
Run X
Run Y
Run Z
Alt Tab +
Alt Tab Tab + +
Alt Delete +
Run Y
Alt Delete +
Alt Tab +

Вывод:
X
Y
Z
Y
X
Y
Z
---------------
```