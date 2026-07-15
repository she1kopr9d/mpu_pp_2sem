import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class WordFrequencyAnalyzer {
    public static void analyze(String inputFile, String outputFile) throws IOException {
        if (!Files.exists(Paths.get(inputFile))) throw new FileNotFoundException("Файл не найден: " + inputFile);
        String text = new String(Files.readAllBytes(Paths.get(inputFile)), StandardCharsets.UTF_8);
        String[] tokens = text.split("\\P{L}+");
        Map<String, Integer> freq = new HashMap<>();
        for (String token : tokens) {
            if (!token.isEmpty()) {
                String word = token.toLowerCase();
                freq.put(word, freq.getOrDefault(word, 0) + 1);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(freq.entrySet());
        list.sort((a, b) -> {
            int cmp = b.getValue().compareTo(a.getValue());
            return cmp != 0 ? cmp : a.getKey().compareTo(b.getKey());
        });
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {
            for (Map.Entry<String, Integer> e : list) {
                writer.printf("%s: %d%n", e.getKey(), e.getValue());
            }
        }
    }
}