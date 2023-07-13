

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {


    public static void main(String[] args) {

        // Scanner sc = new Scanner(System.in);
        System.out.println("Enter the path to log file");
        String logFilePath = "GcmWebServices_Trace.txt";// sc.nextLine();

        long startTime = System.currentTimeMillis(); // Timer

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
        } catch (IOException | ParseException e) {
            System.out.println("File not found");
        }


        long endTime = System.currentTimeMillis(); // Stop the timer
        long executionTime = endTime - startTime;

        System.out.println("Execution time: " + executionTime + " milliseconds");

    }
}
