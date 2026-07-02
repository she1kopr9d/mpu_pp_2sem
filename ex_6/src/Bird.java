import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import shelldev.utils.paint.Brush;
import shelldev.utils.paint.IDrawable;
import shelldev.utils.paint.Screen;

abstract public class Bird implements IPositionable, IDrawable, IRandomable, ISizable {
    private static int count = 0;
    private static final Random rand = new Random();

    private int x;
    private int y;
    private int width;
    private int height;

    public Bird(){
        count++;
        System.out.println("Я птица.");
        //System.out.println("Я птица." + " Всего птиц: " + count + ".");
    }

    public void fly(){
        System.out.println("Я лечу!");
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void randomize() {
        x = rand.nextInt(700);
        y = rand.nextInt(500);
        width = rand.nextInt(Math.min(100, Math.max(20, Math.min(800-x, 100))));
        height = rand.nextInt(Math.min(100, Math.max(20, Math.min(600-y, 100))));
    }

    @Override
    public void draw(Screen screen, Brush brush) {
        brush.setColor(Color.BLUE);
        brush.drawRect(getX(), getY(), width, height);
        brush.setColor(Color.RED);
        brush.drawCircle(getX() + width/2, getY() + height/2, 3);
        brush.setColor(Color.BLACK);
        brush.setFont(new Font("Arial", Font.PLAIN, 10));
        brush.drawText(getClass().getSimpleName(), getX() + 8, getY() - 4);
    }

    public static void printCount(){
        System.out.println("Всего птиц: " + count + ".");
    }

    public boolean isFullyInsideCircle(int circleX, int circleY, int radius) {
        int x1 = getX();
        int y1 = getY();
        int x2 = getX() + getWidth();
        int y2 = getY();
        int x3 = getX();
        int y3 = getY() + getHeight();
        int x4 = getX() + getWidth();
        int y4 = getY() + getHeight();
        return distance(x1, y1, circleX, circleY) <= radius &&
            distance(x2, y2, circleX, circleY) <= radius &&
            distance(x3, y3, circleX, circleY) <= radius &&
            distance(x4, y4, circleX, circleY) <= radius;
    }

    public boolean isIntersectingCircle(int circleX, int circleY, int radius) {
        int x1 = getX();
        int y1 = getY();
        int x2 = getX() + getWidth();
        int y2 = getY();
        int x3 = getX();
        int y3 = getY() + getHeight();
        int x4 = getX() + getWidth();
        int y4 = getY() + getHeight();
        return distance(x1, y1, circleX, circleY) <= radius ||
            distance(x2, y2, circleX, circleY) <= radius ||
            distance(x3, y3, circleX, circleY) <= radius ||
            distance(x4, y4, circleX, circleY) <= radius;
    }

    private double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
