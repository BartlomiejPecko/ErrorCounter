import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class LogItem {
    Date logData; //
    String logTime;

    String type; /* ERROR, INFO, WARN,.. */

    String logPackage;

    String logSubject;  /*EXCEPTION CAUGHT IN [com.scc.gcmreport.gcmfunction.UserDefinedField]*/



    String logContent;

    String logKey;



    public LogItem(String singleLog) throws ParseException {
        String[] parts = singleLog.split(" ");


        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss,SSS");
        String dateTimeString = parts[0] + " " + parts[1];
        logData = dateFormat.parse(dateTimeString);
        logTime = parts[1];
        type = parts[2];
        logPackage = parts[4];

        //logSubject = parts[6];
        //String temp = singleLog.replace(dateTimeString, "").replace(logTime, "").replace(type, "").replace(logPackage, "");

        StringBuilder subjectBuilder = new StringBuilder();
        int subjectStartIndex = 6; // Start index of the log subject

        if (parts.length > subjectStartIndex) {
            for (int i = subjectStartIndex; i < parts.length; i++) {
                subjectBuilder.append(parts[i]);
                if (i < parts.length - 1) {
                    // Check if the next part starts with a new line character
                    if (parts[i + 1].startsWith("\n")) {
                        break; // Stop appending if new line character is encountered
                    }
                    subjectBuilder.append(" ");
                }
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
       // System.out.println("Date: " + logData);
      //  System.out.println("Time: " + logTime);
      //  System.out.println("Type: " + type);
      //  System.out.println("Package: " + logPackage);
        //System.out.println("Subject: " + logSubject);
        //System.out.println("\n");
        //System.out.println("Content: " + logContent);




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



// 12-06-2023 07:24:55,669 ERROR []:151 com.scc.genetools.extensions.CoreClassProvider - Registering externsion classes from :com.scc.ext.registrations.ExtClassManager
// 14-06-2023 10:43:36,659 ERROR []:169 com.scc.gcmreport.remoteprinting.RemotePrintingTokenMaintenance - null

