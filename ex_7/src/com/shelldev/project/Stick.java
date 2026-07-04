package com.shelldev.project;

import com.shelldev.project.esc.Entity;
import com.shelldev.project.esc.components.Position;
import com.shelldev.project.esc.components.Sprite;
import com.shelldev.project.math.Point;
import java.awt.Color;

public class Stick extends Entity implements IRandomable {
    private int _length;
    private static final java.util.Random rand = new java.util.Random();

    public Stick() {
        Position pos = new Position(0, 0);
        pos.setPoint(new Point(rand.nextInt(800), rand.nextInt(600)));
        _length = rand.nextInt(100, 500);
        super.addComponent(pos);
        super.addComponent(new Sprite((screen, brush) -> {
            brush.setColor(Color.DARK_GRAY);
            brush.drawLine(pos.getPoint().getX(), pos.getPoint().getY(), pos.getPoint().getX() + _length, pos.getPoint().getY()+1);
        }));
    }

    @Override
    public void randomize() {
        Position pos = super.getComponent(Position.class);
        int x = rand.nextInt(400);
        pos.setPoint(new Point(x, rand.nextInt(600)));
        _length = rand.nextInt(200, 600-x);
    }

    public int getLength() {
        return _length;
    }
}
