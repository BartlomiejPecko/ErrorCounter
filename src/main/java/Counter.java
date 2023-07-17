import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.*;
import java.util.*;

public class Counter {
    public static void countKeyOccurrences(String inputFilePath, String outputFilePath) {
        Map<String, Integer> keyCounts = new HashMap<>(); //map storing count of keys

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String key = getKeyFromLine(line);
                if (key != null) {
                    keyCounts.put(key, keyCounts.getOrDefault(key, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        saveToTextFile(keyCounts, outputFilePath);
        saveToJson(keyCounts, outputFilePath);
    }

    private static String getKeyFromLine(String line) {
        if (!line.isEmpty() && Character.isDigit(line.charAt(0))) {
            String[] parts = line.split(" ");
            for (String part : parts) {
                if (part.startsWith("com.scc")) {
                    return part.replaceAll("[0-9]", "x");
                }
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

