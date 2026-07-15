import java.io.IOException;

public class Main {
    private static void ex_1(){
        try {
            WordFrequencyAnalyzer.analyze("./data/input.txt", "./data/output.txt");
        } catch (IOException e) {
        }
    }

    private static void ex_2(){
        PhoneBook.start();
    }

    public static void main(String[] args) {
        ex_1();
        ex_2();
    }
}