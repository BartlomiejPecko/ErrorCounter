// dodac timery
// id inne = ten sam log
// klasa postać z "XXXX" zawierajaca poszczegolne wystapienia
// dane do pliku
// historia wystepowania błędów, rozne dni
//jakie problemy z danej klasy

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static com.sun.tools.javac.util.Log.logKey;


public class Main {

    public static void main(String[] args) {




        //Scanner sc = new Scanner(System.in);

        System.out.println("Enter the path to log file");
        String logFilePath = "GcmWebServices_Trace.txt";//sc.nextLine();


        long startTime = System.currentTimeMillis(); // timer


        try {
            Map<String, Integer> errorCounts = countErrors(logFilePath);
            for (Map.Entry<String, Integer> entry : errorCounts.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue()); //print errorów
            }
        } catch (IOException | ParseException e) {
            System.out.println("File not found");
        }
        long endTime = System.currentTimeMillis(); // Stop the timer
        long executionTime = endTime - startTime;

        System.out.println("Execution time: " + executionTime + " milliseconds");



        }

    public static Map<String, Integer> countErrors(String logFilePath) throws IOException, ParseException {
        Map<String, Integer> errorCounts = new HashMap<>();

        List<String> XXX = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                if(line.length() > 0) {
                    char firstChar = line.charAt(0);
                    if (firstChar >= '0' && firstChar <= '9') {
                        if (!sb.isEmpty()) {
                            XXX.add(sb.toString());
                            sb.setLength(0);
                        }

                    }
                    sb.append(line);
                }
//                String error = extractError(line);
//                if (error != null) {
//                    errorCounts.put(error, errorCounts.getOrDefault(error, 0) + 1);
//                    XXX.add(line);
//
//
//                }
            }

        }

        List<LogItem> logItemList = new ArrayList<>();
       // List<String> logKeys = new ArrayList<>();

        for(String singleLog : XXX){
            logItemList.add(new LogItem(singleLog, LogItem.logKeys));

        }



        //System.out.println(x);

        return errorCounts;

    }
}
