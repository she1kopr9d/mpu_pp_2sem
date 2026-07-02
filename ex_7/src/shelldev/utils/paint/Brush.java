package shelldev.utils.paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Brush {
    private final Graphics2D g2d;

    public Brush(Graphics2D g2d) {
        this.g2d = g2d;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void setColor(Color color) {
        g2d.setColor(color);
    }

    public void setLineWidth(double width) {
        g2d.setStroke(new java.awt.BasicStroke((float) width));
    }

    public void setFont(Font font) {
        g2d.setFont(font);
    }

    public void drawCircle(double x, double y, double radius) {
        int r = (int) Math.round(radius);
        int ix = (int) Math.round(x);
        int iy = (int) Math.round(y);
        g2d.fillOval(ix - r, iy - r, 2 * r, 2 * r);
    }

    public void drawCircleOutline(double x, double y, double radius) {
        int r = (int) Math.round(radius);
        int ix = (int) Math.round(x);
        int iy = (int) Math.round(y);
        g2d.drawOval(ix - r, iy - r, 2 * r, 2 * r);
    }

    public void drawRect(double x, double y, double width, double height) {
        g2d.drawRect((int) Math.round(x), (int) Math.round(y),
                     (int) Math.round(width), (int) Math.round(height));
    }

    public void drawLine(double x1, double y1, double x2, double y2) {
        g2d.drawLine((int) Math.round(x1), (int) Math.round(y1),
                     (int) Math.round(x2), (int) Math.round(y2));
    }

    public void drawText(String text, double x, double y) {
        g2d.drawString(text, (int) Math.round(x), (int) Math.round(y));
    }
}