import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogItem {
    Date logData;
    String logTime;
    String type;
    String logPackage;
    String logSubject;
    String logContent;
    protected static String logKey;
    protected static List<String> logKeys = new ArrayList<>();

    public LogItem(String singleLog) throws ParseException {
        String[] parts = singleLog.split(" ");

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss,SSS");
        String dateTimeString = parts[0] + " " + parts[1];
        logData = dateFormat.parse(dateTimeString);
        logTime = parts[1];
        type = parts[2];
        logPackage = parts[4];

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

        int contentStartIndex = 6;

        StringBuilder contentBuilder = new StringBuilder();
        for (int i = contentStartIndex; i < parts.length; i++) {
            contentBuilder.append(parts[i]);
            if (i < parts.length - 1) {
                contentBuilder.append(" ");
            }
        }

        logContent = contentBuilder.toString();
        logKey = logPackage + " - " + logSubject;
        logKeys.add(logKey);

        Map<String, Integer> logKeyCountMap = new HashMap<>();

        for (String logKey : logKeys) {
            logKeyCountMap.put(logKey, logKeyCountMap.getOrDefault(logKey, 0) + 1);

        }
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
    public String getLogKey() {
        return logKey;
    }

    public List<String> getErrors() {
        return logKeys;
    }
}





