import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogItem {
    Date logData; //
    String logTime;

    String type; /* ERROR, INFO, WARN,.. */

    String logPackage;

    String logSubject;  /*EXCEPTION CAUGHT IN [com.scc.gcmreport.gcmfunction.UserDefinedField]*/

    String logContent;

    public LogItem(String singleLog) throws ParseException {
        String[] parts = singleLog.split(" ");


        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss,SSS");
        String dateTimeString = parts[0] + " " + parts[1];
        logData = dateFormat.parse(dateTimeString);
        logTime = parts[1];
        type = parts[2];
        logPackage = parts[4];
        logSubject = parts[6];


       logContent = singleLog.substring(singleLog.indexOf(" - ") + 3);

        //System.out.println("Date: " + logData);
        //System.out.println("Time: " + logTime);
        //System.out.println("Type: " + type);
        //System.out.println("Package: " + logPackage);
        //System.out.println("Subject: " + logSubject);
        //System.out.println("Content: " + logContent);


    }
}
