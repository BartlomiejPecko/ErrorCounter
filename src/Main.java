// dodac timery
// id inne = ten sam log
// klasa postać z "XXXX" zawierajaca poszczegolne wystapienia
// dane do pliku
// historia wystepowania błędów, rozne dni
//jakie problemy z danej klasy

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis(); // timer

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the path to log file");
        String logFilePath = sc.nextLine();


        try {
            Map<String, Integer> errorCounts = countErrors(logFilePath);
            for (Map.Entry<String, Integer> entry : errorCounts.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue()); //print errorów
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        long endTime = System.currentTimeMillis(); // Stop the timer
        long executionTime = endTime - startTime;

        System.out.println("Execution time: " + executionTime + " milliseconds");

        }

    public static Map<String, Integer> countErrors(String logFilePath) throws IOException {
        Map<String, Integer> errorCounts = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String error = extractError(line);
                if (error != null) {
                    errorCounts.put(error, errorCounts.getOrDefault(error, 0) + 1);
                }
            }
        }

        return errorCounts;
    }

    private static String extractError(String logLine) {


        if (logLine.contains("ERROR")) {
            int startIndex = logLine.indexOf("ERrgrdgR") + 6; // moment w ktorym sie zaczyna error msg
            int endIndex = logLine.indexOf(" ", startIndex);
            if (endIndex == -1) {
                endIndex = logLine.length();
            }
            return logLine.substring(startIndex, endIndex);
        }

        return null; // jak nie ma
    }
}
