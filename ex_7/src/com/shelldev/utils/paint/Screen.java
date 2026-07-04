package com.shelldev.utils.paint;

import java.awt.Graphics2D;

public class Screen {
    private final Graphics2D g2d;
    private final int width;
    private final int height;

    public Screen(Graphics2D g2d, int width, int height) {
        this.g2d = g2d;
        this.width = width;
        this.height = height;
    }

    public Graphics2D getGraphics2D() { return g2d; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}