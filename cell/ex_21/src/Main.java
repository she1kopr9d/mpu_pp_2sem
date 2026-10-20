import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    private static String generateFileName(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".txt";
    }
    public static void initializationWindow(Window[] panelHolder, ConvexWithFile convex, String fileName) throws Exception{
        SwingUtilities.invokeAndWait(() -> {
            Window panel = new Window(convex);
            panelHolder[0] = panel;
            JFrame frame = new JFrame("Выпуклая оболочка — " + fileName);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    public static void main(String[] args) throws Exception{
        final ConvexWithFile convex;
        final Window[] panelHolder = new Window[1];
        final Plugin[] plugins = new Plugin[] {
            new Plugin6()
        };
        String fileName = generateFileName();
        convex = new ConvexWithFile("logs/" + fileName);
        convex.includePlugin(plugins);
        initializationWindow(panelHolder, convex, fileName);
        Window panel = panelHolder[0];
        System.out.println("Лог пишется в logs/" + fileName);
        try (convex) {
            while (true) {
                convex.add(new R2Point());
                System.out.println("S = " + convex.area()
                                 + ", P = " + convex.perimeter());
                SwingUtilities.invokeLater(panel::repaint);
            }
        }
    }
}
