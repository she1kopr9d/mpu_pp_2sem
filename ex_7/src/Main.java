import com.shelldev.project.birds.Bird;
import com.shelldev.project.birds.Parrot;
import com.shelldev.project.birds.Penguin;
import com.shelldev.project.esc.Entity;
import com.shelldev.project.esc.System;
import com.shelldev.project.esc.systems.RenderSystem;
import com.shelldev.utils.paint.Brush;
import com.shelldev.utils.paint.Screen;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {
    public static Bird[] loadMoc(){
        Parrot p1 = new Parrot();
        Parrot p2 = new Parrot("Клёпа");
        Penguin p3 = new Penguin();
        Bird p4 = new Parrot();
        Penguin p5 = new Penguin();
        Bird[] birds = new Bird[]{p1, p2, p3, p4, p4, p5};
        return birds;
    }

    public static void main(String[] args){
        // ArrayList<IDrawable> drawableObjects = new ArrayList<IDrawable>();
        ArrayList<Entity> entities = new ArrayList<>();
        Bird[] birds = loadMoc();
        for (Bird bird : birds){
            bird.randomize();
            entities.add(bird);
        }
        ArrayList<System> systems = new ArrayList<>();
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
                    systems.add(new RenderSystem(screen, brush));
                    for (System system : systems){
                        system.Update(entities, 0);
                    }
                }
            };
            frame.setContentPane(panel);
            frame.setVisible(true);
        });
    }
}
