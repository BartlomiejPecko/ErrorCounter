

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Counter {

    public static List<String> countErrors(String logFilePath) throws IOException, ParseException {
        List<String> uniqueErrorList = new ArrayList<>();

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
            for (String error : logItem.getErrors()) {
                if (!uniqueErrorList.contains(error)) {
                    uniqueErrorList.add(error);
                }
            }
        }

        return uniqueErrorList;
    }
}

