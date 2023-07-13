import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Counter {

    public static Set<String> countErrors(String logFilePath) throws IOException, ParseException {
        Set<String> uniqueErrors = new HashSet<>();

        List<String> XXX = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                if (line.length() > 0) {
                    char firstChar = line.charAt(0);
                    if (firstChar >= '0' && firstChar <= '9') {
                        if (!sb.isEmpty()) {
                            XXX.add(sb.toString());
                            sb.setLength(0);
                        }
                    }
                    sb.append(line);
                }
            }
        }

        List<LogItem> logItemList = new ArrayList<>();

        for (String singleLog : XXX) {
            LogItem logItem = new LogItem(singleLog);
            logItemList.add(logItem);
            uniqueErrors.addAll(logItem.getErrors());


        }

        return uniqueErrors;
    }
}

