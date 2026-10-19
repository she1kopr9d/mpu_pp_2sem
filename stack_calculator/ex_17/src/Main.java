import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Compf c = new Compf();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Введите формулу -> ");
            if (!sc.hasNextLine()) break;
            String line = sc.nextLine().trim();
            if (line.isEmpty()) break;

            c = new Compf();
            c.compile(line.toCharArray());
        }
    }
}
