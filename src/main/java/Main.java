import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter the path to log file");
        String logFilePath = "GcmWebServices_Trace.txt";

        long startTime = System.currentTimeMillis();

        try {
            List<String> uniqueErrors = Counter.countErrors(logFilePath);
            int totalUniqueErrors = uniqueErrors.size();

            System.out.println("Total count of unique errors: " + totalUniqueErrors);
            System.out.println("Unique errors:");

            Map<String, Integer> errorCounts = new HashMap<>();
            for (String error : uniqueErrors) {
                errorCounts.put(error, errorCounts.getOrDefault(error, 0) + 1);
            }

            for (Map.Entry<String, Integer> entry : errorCounts.entrySet()) {
                String error = entry.getKey();
                int count = entry.getValue();
                System.out.println(error + ": " + count + " times");
            }

            // Convert errorCounts map to JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(errorCounts);

            // Save JSON to a file
            try (FileWriter writer = new FileWriter("error_counts.json")) {
                writer.write(json);
            }

            System.out.println("Error counts saved to error_counts.json");
        } catch (IOException | ParseException e) {
            System.out.println("File not found");
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Execution time: " + executionTime + " milliseconds");
    }
}