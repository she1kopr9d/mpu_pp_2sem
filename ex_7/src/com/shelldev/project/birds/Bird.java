package com.shelldev.project.birds;
import com.shelldev.project.IRandomable;
import com.shelldev.project.esc.Entity;
import com.shelldev.project.esc.components.Position;
import com.shelldev.project.esc.components.Size;
import com.shelldev.project.esc.components.Sprite;
import com.shelldev.project.math.Point;
import java.awt.Color;
import java.util.Random;


abstract public class Bird extends Entity implements IRandomable{
    private static int count = 0;
    private static final Random rand = new Random();

    public Bird(){
        count++;
        java.lang.System.out.println("Я птица.");
        //java.lang.System.out.println("Я птица." + " Всего птиц: " + count + ".");
        Position pos = new Position(0, 0);
        int m1 = rand.nextInt(50, 100);
        int m2 = rand.nextInt(m1, 100);
        Size size = new Size(m1, m2);
        super.addComponent(pos);
        super.addComponent(size);
        super.addComponent(new Sprite((screen, brush) -> {
            Point position = pos.getPoint();
            brush.setColor(Color.ORANGE);
            brush.drawRect(position.getX(), position.getY(), size.getWidth(), size.getHeight());
        }));
    }

    public void fly(){
        java.lang.System.out.println("Я лечу!");
    }

    @Override
    public void randomize() {
        Position pos = super.getComponent(Position.class);
        pos.setPoint(new Point(rand.nextInt(600), rand.nextInt(400)));

    }

    public static void printCount(){
        java.lang.System.out.println("Всего птиц: " + count + ".");
    }
}
