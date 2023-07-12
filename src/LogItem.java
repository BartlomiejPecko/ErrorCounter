import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogItem {
    Date logData; //
    String logTime;

    String type; /* ERROR, INFO, WARN,.. */

    String logPackage;

    String logSubject;  /*EXCEPTION CAUGHT IN [com.scc.gcmreport.gcmfunction.UserDefinedField]*/


    String logContent;

    static String logKey;

    static List<String> logKeys = new ArrayList<>();

    public LogItem(String singleLog, List<String> logKeys) throws ParseException {
        String[] parts = singleLog.split(" ");


        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss,SSS");
        String dateTimeString = parts[0] + " " + parts[1];
        logData = dateFormat.parse(dateTimeString);
        logTime = parts[1];
        type = parts[2];
        logPackage = parts[4];

        //logSubject = parts[6];


        StringBuilder subjectBuilder = new StringBuilder();
        int subjectStartIndex = 6; // Start index of the log subject

        if (parts.length > subjectStartIndex) {
            for (int i = subjectStartIndex; i < parts.length; i++) {
                subjectBuilder.append(parts[i]);
                if (i < parts.length - 1) {
                    // Check if the next part starts with a new line character
                   // if (parts[i + 1].startsWith("\n")) {
                        break; // Stop appending if new line character is encountered
                    //}
                  //  subjectBuilder.append(" ");
                }subjectBuilder.append(" ");  //TODO  //Cuts keys too early
            }

        }

        logSubject = subjectBuilder.toString();
        logSubject = logSubject.replaceAll("\\d+", "XXX");
       // logSubject = subjectBuilder.toString();



        StringBuilder contentBuilder = new StringBuilder();
        for (int i = 6; i < parts.length; i++) {
            contentBuilder.append(parts[i]);
            if (i < parts.length - 1) {
                contentBuilder.append(" ");
            }
        }

        logContent = contentBuilder.toString();
        logKey = logPackage + " - " + logSubject;
        logKeys.add(logKey);
        //System.out.println("Date: " + logData);
        //System.out.println("Time: " + logTime);
        //System.out.println("Type: " + type);
        //System.out.println("Package: " + logPackage);
        //System.out.println("Subject: " + logSubject);
        //System.out.println("\n");
        //System.out.println("Content: " + logContent);


        Map<String, Integer> logKeyCountMap = new HashMap<>();
        for (String logKey : logKeys) {
            if (logKeyCountMap.containsKey(logKey)) {
                logKeyCountMap.put(logKey, logKeyCountMap.get(logKey) + 1);
            } else {
                logKeyCountMap.put(logKey, 1);
            }
        }


        for (Map.Entry<String, Integer> entry : logKeyCountMap.entrySet()) {
            String logKey = entry.getKey();
            int count = entry.getValue();

            System.out.println(logKey + " ---> " + count + " (number of repetitions)");
        }

        int totalErrors = logKeyCountMap.size();
        System.out.println("Total count of separate errors: " + totalErrors);
    }



    @Override
    public String toString() {
        return "Date: " + logData +
                "\nTime: " + logTime +
                "\nType: " + type +
                "\nPackage: " + logPackage +
                "\nSubject: " + logSubject +
                "\n";

    }


}





