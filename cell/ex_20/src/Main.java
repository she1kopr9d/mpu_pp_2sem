import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static String generateFileName(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".txt";
    }
    public static void main(String[] args) throws Exception{
        String fileName = generateFileName();
        try (ConvexWithFile convex = new ConvexWithFile("logs/" + fileName)) {
            System.out.println("Лог пишется в " + fileName);
            while (true) {
                convex.add(new R2Point());
                System.out.println("S = " + convex.area() + ", P = " + convex.perimeter());
            }
        }
    }
}
