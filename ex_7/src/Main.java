import com.shelldev.project.Stick;
import com.shelldev.project.birds.Bird;
import com.shelldev.project.birds.Parrot;
import com.shelldev.project.birds.Penguin;
import com.shelldev.project.esc.Entity;
import com.shelldev.project.esc.components.Position;
import com.shelldev.project.esc.components.Size;
import com.shelldev.project.esc.systems.RenderSystem;
import com.shelldev.project.math.Point;
import com.shelldev.utils.paint.Brush;
import com.shelldev.utils.paint.Screen;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {
    private static ArrayList<Entity> entities = new ArrayList<>();
    private static JPanel panel;

    public static Bird[] loadMoc() {
        Parrot p1 = new Parrot();
        Parrot p2 = new Parrot("Клёпа");
        Penguin p3 = new Penguin();
        Bird p4 = new Parrot();
        Penguin p5 = new Penguin();
        return new Bird[]{p1, p2, p3, p4, p4, p5};
    }

    private static void startWindow(Runnable gameLoop) {
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

                    RenderSystem rs = new RenderSystem(screen, brush);
                    rs.Start(entities);
                    rs.Update(entities, 0);
                }
            };

            frame.setContentPane(panel);
            frame.setVisible(true);
            Main.panel = panel;

            new Thread(gameLoop).start();
        });
    }

    private static int getMaxWidthBird(Bird[] birds) {
        int maxWidth = 0;
        for (Bird bird : birds) {
            int width = bird.getComponent(com.shelldev.project.esc.components.Size.class).getWidth();
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        return maxWidth;
    }

    private static int getPlacesForBirds(Bird[] birds, Stick stick) {
        int maxWidth = getMaxWidthBird(birds);
        return stick.getLength() / maxWidth;
    }

    private static void placeBirdsOnStick(Bird[] birds, Stick stick) {
        int splitIndex = getPlacesForBirds(birds, stick);
        int maxWidth = getMaxWidthBird(birds);
        Bird[] first = new Bird[splitIndex];
        Bird[] second = new Bird[birds.length - splitIndex];

        java.lang.System.arraycopy(birds, 0, first, 0, splitIndex);
        java.lang.System.arraycopy(birds, splitIndex, second, 0, birds.length - splitIndex);
        for (Bird bird : second) {
            bird.randomize();
        }

        int x = (int)stick.getComponent(Position.class).getPoint().getX();
        int y = (int)stick.getComponent(Position.class).getPoint().getY();

        for (int i = 0; i < first.length; i++) {
            Bird bird = first[i];
            int x_offset  = x + i * maxWidth;
            Size size = bird.getComponent(Size.class);
            Position position = bird.getComponent(Position.class);
            position.setPoint(new Point(x_offset, y-size.getHeight()));
        }
    }

    public static void main(String[] args) {
        Bird[] birds = loadMoc();
        for (Bird bird : birds) {
            bird.randomize();
            entities.add(bird);
        }
        Stick stick = new Stick();
        entities.add(stick);
        stick.randomize();
        startWindow(() -> {
            Scanner scanner = new Scanner(java.lang.System.in);
            String cmd = "view";
            while (!cmd.equals("exit")) {
                switch (cmd) {
                    case "view":
                        if (panel != null) {
                            panel.repaint();
                        }
                        break;
                    case "rnd":
                        for (Entity entity : entities) {
                            if (entity instanceof Bird) {
                                ((Bird) entity).randomize();
                            }
                        }
                        stick.randomize();
                        if (panel != null) {
                            panel.repaint();
                        }
                        break;
                    case "stick":
                        placeBirdsOnStick(birds, stick);
                        if (panel != null) {
                            panel.repaint();
                        }
                        break;
                    case "help":
                        java.lang.System.out.println("Список команд:");
                        java.lang.System.out.println("view - отобразить птиц");
                        java.lang.System.out.println("rnd - рандомизировать позиции птиц");
                        java.lang.System.out.println("stick - разместить птиц на палке");
                        java.lang.System.out.println("exit - выйти из программы");
                        break;
                    default:
                        java.lang.System.out.println("Неизвестная команда: " + cmd);
                        break;
                }
                java.lang.System.out.print("Введите команду (help - список команд): ");
                cmd = scanner.nextLine();
            }
            java.lang.System.exit(0);
        });
    }
}