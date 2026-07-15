# Задание 12. Использование контейнеров

## Формулировка задания

Используя java.util.LinkedList, решите Задание 4.

## Отчет по заданию

### Список проделанных действий

- Адаптировал код [Задания 11](../ex_11/) под реализацию java.util.LinkedList.

### Вывод программы (Идентичен выводу программы 11)

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