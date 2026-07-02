import java.awt.Color;
import shelldev.utils.paint.Brush;
import shelldev.utils.paint.IDrawable;
import shelldev.utils.paint.Screen;

public class CircleDrawable implements IDrawable {
    private final int cx, cy, radius;
    private final Color color;

    public CircleDrawable(int cx, int cy, int radius, Color color) {
        this.cx = cx; this.cy = cy; this.radius = radius; this.color = color;
    }

    @Override
    public void draw(Screen screen, Brush brush) {
        brush.setColor(Color.RED);
        brush.drawCircle(cx, cy, 3);
        brush.setColor(color);
        brush.setLineWidth(1);
        brush.drawCircleOutline(cx, cy, radius);
    }
}