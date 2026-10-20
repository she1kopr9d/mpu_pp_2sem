import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

class Window extends JPanel {
    private final Convex _convex;

    public Window(Convex convex) {
        _convex = convex;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();
        if (w <= 0 || h <= 0) return;
        BufferedImage buffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = buffer.createGraphics();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, w, h);
            drawScene(g2, w, h);
        } finally {
            g2.dispose();
        }
        g.drawImage(buffer, 0, 0, null);
    }

    private void drawScene(Graphics2D g2, int w, int h) {
        R2Point[] verts = _convex.getPoints();
        double minX = -1.0, maxX = 1.0, minY = -1.0, maxY = 1.0;
        if (verts.length > 0) {
            minX = maxX = verts[0].getX();
            minY = maxY = verts[0].getY();
            for (R2Point p : verts) {
                if (p.getX() < minX) minX = p.getX();
                if (p.getX() > maxX) maxX = p.getX();
                if (p.getY() < minY) minY = p.getY();
                if (p.getY() > maxY) maxY = p.getY();
            }
        }
        minX = Math.min(minX, 0.0);
        maxX = Math.max(maxX, 0.0);
        minY = Math.min(minY, 0.0);
        maxY = Math.max(maxY, 0.0);
        double spanX = Math.max(maxX - minX, 1e-9);
        double spanY = Math.max(maxY - minY, 1e-9);
        final double margin = 40.0;
        double scale = Math.min((w - 2 * margin) / spanX, (h - 2 * margin) / spanY);
        if (!Double.isFinite(scale) || scale <= 0.0) scale = 1.0;
        double cx = (minX + maxX) / 2.0;
        double cy = (minY + maxY) / 2.0;
        int ox = screenX(0.0, cx, scale, w);
        int oy = screenY(0.0, cy, scale, h);
        g2.setColor(new Color(200, 200, 200));
        if (ox >= 0 && ox < w) g2.drawLine(ox, 0, ox, h - 1);
        if (oy >= 0 && oy < h) g2.drawLine(0, oy, w - 1, oy);
        if (verts.length == 1) {
            drawVertex(g2, verts[0], cx, cy, scale, w, h);
        } else if (verts.length == 2) {
            R2Point a = verts[0], b = verts[1];
            g2.setColor(Color.BLUE);
            g2.drawLine(
                screenX(a.getX(), cx, scale, w),
                screenY(a.getY(), cy, scale, h),
                screenX(b.getX(), cx, scale, w),
                screenY(b.getY(), cy, scale, h)
            );
            drawVertex(g2, a, cx, cy, scale, w, h);
            drawVertex(g2, b, cx, cy, scale, w, h);
        } else if (verts.length >= 3) {
            int n = verts.length;
            int[] xs = new int[n];
            int[] ys = new int[n];
            for (int i = 0; i < n; i++) {
                xs[i] = screenX(verts[i].getX(), cx, scale, w);
                ys[i] = screenY(verts[i].getY(), cy, scale, h);
            }
            java.awt.Polygon awtPoly = new java.awt.Polygon(xs, ys, n);
            g2.setColor(new Color(0, 0, 255, 40));
            g2.fill(awtPoly);
            g2.setColor(Color.BLUE);
            g2.draw(awtPoly);
            for (R2Point p : verts) drawVertex(g2, p, cx, cy, scale, w, h);
        }
    }

    private int screenX(double x, double cx, double scale, int w) {
        return (int) Math.round(w / 2.0 + (x - cx) * scale);
    }

    private int screenY(double y, double cy, double scale, int h) {
        return (int) Math.round(h / 2.0 - (y - cy) * scale);
    }

    private void drawVertex(Graphics2D g2, R2Point p, double cx, double cy, double scale, int w, int h) {
        int x = screenX(p.getX(), cx, scale, w);
        int y = screenY(p.getY(), cy, scale, h);
        g2.setColor(Color.BLUE);
        g2.fillOval(x - 3, y - 3, 6, 6);
    }
}