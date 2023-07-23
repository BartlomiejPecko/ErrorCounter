import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Counter {

    public static void countKeyOccurrences(String inputFilePath, String outputFilePath) throws IOException {
        Map<String, Integer> keyCounts;

        Map<String, Integer> sortedKeyCounts;
        LinkedHashMap<String, Integer> top10KeyCounts;

        try (Stream<String> lines = Files.lines(Paths.get(inputFilePath))) {
            keyCounts = lines
                    .filter(line -> !line.isEmpty() && Character.isDigit(line.charAt(0)))
                    .map(Counter::getKeyFromLine)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toMap(
                            key -> key.replaceAll("[0-9]", "x"),
                            e -> 1,
                            Integer::sum,
                            HashMap::new
                    ));

            sortedKeyCounts = keyCounts.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new));


            top10KeyCounts = sortedKeyCounts.entrySet()
                    .stream()
                    .limit(12)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new));


        } catch (FileNotFoundException e) {
            // Catch the FileNotFoundException and rethrow it as an IOException
            throw new IOException("File not found: " + e.getMessage());
        }

        saveToTextFile(keyCounts, outputFilePath);
        saveToJson(sortedKeyCounts, outputFilePath);
        ChartGenerator.generateChart(top10KeyCounts, outputFilePath);
    }

    private static String getKeyFromLine(String line) {
        String[] parts = line.split(" ");
        for (String part : parts) {
            if (part.startsWith("com.scc")) {
                return part.replaceAll("[0-9]", "x");
            }
        }
        return null;
    }

    private static void saveToTextFile(Map<String, Integer> keyCounts, String outputFilePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {
            for (Map.Entry<String, Integer> entry : keyCounts.entrySet()) {
                writer.println(entry.getKey() + " <-- repeated " + entry.getValue() + " times");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveToJson(Map<String, Integer> keyCounts, String outputFilePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry<String, Integer> entry : keyCounts.entrySet()) {
            jsonObject.addProperty(entry.getKey(), entry.getValue());
        }

        try (Writer writer = new FileWriter(outputFilePath.replace(".txt", ".json"))) {
            gson.toJson(jsonObject, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
