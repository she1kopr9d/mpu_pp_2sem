import shelldev.utils.valid_input.Filter;
import shelldev.utils.valid_input.Input;
import shelldev.utils.valid_input.filter_collection.InRangeFilter;
import shelldev.utils.valid_input.filter_collection.IntFilter;

public class Main {
    public static void loadMoc(){
        Parrot p1 = new Parrot();
        Parrot p2 = new Parrot("Клёпа");
        Penguin p3 = new Penguin();
        Bird p4 = new Parrot();
        Bird[] birds = new Bird[]{p1, p2, p3, p4, p4};
        Penguin p5 = new Penguin();
        p1.hello(p2);
        p1.hello(p1);
        p1.hello((Parrot)p4);
        p1.hello(p3);
        p3.hello(p1);
        p3.hello(p5);
    }
    public static void main(String[] args){
        loadMoc();
        Filter[] radiusFilters = new Filter[] {
            new InRangeFilter(0.0, 1000.0),
            new IntFilter()
        };
        String radiusStr = Input.getLine(radiusFilters, "Ошибка! Введите радиус (целое число от 0 до 1000, границы не включены).", "Введите радиус: ");
        int radius = Integer.parseInt(radiusStr);
        Filter[] coordFilters = new Filter[] {
            new InRangeFilter(0, 100),
            new IntFilter()
        };
        String xStr = Input.getLine(coordFilters, "Ошибка! Введите целое x от 0 до 100 (границы не включены).", "Введите x: ");
        int x = Integer.parseInt(xStr);
        String yStr = Input.getLine(coordFilters, "Ошибка! Введите целое y от 0 до 100 (границы не включены).", "Введите y: ");
        int y = Integer.parseInt(yStr);
        System.out.println("Радиус: " + radius + ", x: " + x + ", y: " + y);
    }
}
