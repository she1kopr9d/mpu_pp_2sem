import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import shelldev.utils.paint.Brush;
import shelldev.utils.paint.IDrawable;
import shelldev.utils.paint.Screen;
import shelldev.utils.valid_input.Filter;
import shelldev.utils.valid_input.Input;
import shelldev.utils.valid_input.filter_collection.InRangeFilter;
import shelldev.utils.valid_input.filter_collection.InSectionFilter;
import shelldev.utils.valid_input.filter_collection.IntFilter;

public class Main {
    public static Bird[] loadMoc(){
        Parrot p1 = new Parrot();
        Parrot p2 = new Parrot("Клёпа");
        Penguin p3 = new Penguin();
        Bird p4 = new Parrot();
        Penguin p5 = new Penguin();
        Bird[] birds = new Bird[]{p1, p2, p3, p4, p4, p5};
        // p1.hello(p2);
        // p1.hello(p1);
        // p1.hello((Parrot)p4);
        // p1.hello(p3);
        // p3.hello(p1);
        // p3.hello(p5);
        return birds;
    }
    public static void render(ArrayList<IDrawable> drawables){
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Птицы и геометрия");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                    Screen screen = new Screen(g2d, getWidth(), getHeight());
                    Brush brush = new Brush(g2d);
                    for (IDrawable d : drawables) {
                        d.draw(screen, brush);
                    }
                }
            };
            frame.setContentPane(panel);
            frame.setVisible(true);
        });
    }

    public static <T extends IPositionable & ISizable> BoundingBox computeBoundingBox(T[] objects, Color color) {
        if (objects == null || objects.length == 0) {
            return null;
        }
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;

        for (T obj : objects) {
            int cx = obj.getX() + obj.getWidth() / 2;
            int cy = obj.getY() + obj.getHeight() / 2;
            if (cx < minX) minX = cx;
            if (cx > maxX) maxX = cx;
            if (cy < minY) minY = cy;
            if (cy > maxY) maxY = cy;
        }

        return new BoundingBox(minX, minY, maxX, maxY, color);
    }

    public static void collectGroupRect(ArrayList<IDrawable> drawableObjects, Bird[] birds){
        Map<Class<? extends Bird>, List<Bird>> groups = new HashMap<>();
        for (Bird b : birds) {
            Class<? extends Bird> cls = b.getClass();
            groups.computeIfAbsent(cls, k -> new ArrayList<>()).add(b);
        }
        Color[] colors = {Color.MAGENTA, Color.ORANGE, Color.PINK, Color.CYAN, Color.YELLOW};
        int colorIndex = 0;
        for (Map.Entry<Class<? extends Bird>, List<Bird>> entry : groups.entrySet()) {
            List<Bird> list = entry.getValue();
            if (list.size() > 1) {
                Bird[] arr = list.toArray(new Bird[0]);
                Color color = colors[colorIndex % colors.length];
                BoundingBox box = computeBoundingBox(arr, color);
                if (box != null) {
                    drawableObjects.add(box);
                }
                colorIndex++;
            }
        }
    }

    public static void main(String[] args){
        ArrayList<IDrawable> drawableObjects = new ArrayList<IDrawable>();
        Bird[] birds = loadMoc();
        for (Bird bird : birds){
            bird.randomize();
            drawableObjects.add(bird);
        }

        Filter[] radiusFilters = new Filter[] {
            new InSectionFilter(1, 300),
            new IntFilter()
        };
        String radiusStr = Input.getLine(radiusFilters, "Ошибка! Введите радиус (целое число от 1 до 300, границы включены).", "Введите радиус: ");
        int radius = Integer.parseInt(radiusStr);
        Filter[] coordFilters = new Filter[] {
            new InRangeFilter(10, 590),
            new IntFilter()
        };
        String xStr = Input.getLine(coordFilters, "Ошибка! Введите целое x от 10 до 590 (границы не включены).", "Введите x: ");
        int x = Integer.parseInt(xStr);
        String yStr = Input.getLine(coordFilters, "Ошибка! Введите целое y от 10 до 590 (границы не включены).", "Введите y: ");
        int y = Integer.parseInt(yStr);
        System.out.println("Радиус: " + radius + ", x: " + x + ", y: " + y);
        int fullyInside = 0;
        for (Bird bird : birds) {
            if (bird.isFullyInsideCircle(x, y, radius)) {
                fullyInside++;
            }
        }
        System.out.println("Полностью внутри окружности: " + fullyInside);
        drawableObjects.add(new CircleDrawable(x, y, radius, Color.CYAN));
        drawableObjects.add(computeBoundingBox(birds, Color.GREEN));
        collectGroupRect(drawableObjects, birds);
        render(drawableObjects);
    }
}
