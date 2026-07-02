import java.awt.Color;
import shelldev.utils.paint.Brush;
import shelldev.utils.paint.IDrawable;
import shelldev.utils.paint.Screen;

public class BoundingBox implements IDrawable {
    private final int minX, minY, maxX, maxY;
    private final Color color;

    public BoundingBox(int minX, int minY, int maxX, int maxY, Color color) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.color = color;
    }

    @Override
    public void draw(Screen screen, Brush brush) {
        brush.setColor(color);
        brush.setLineWidth(2);
        brush.drawRect(minX, minY, maxX - minX, maxY - minY);
    }
}