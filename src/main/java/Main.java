import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter the file path: ");
//        String inputFilePath = sc.nextLine();

        long startTime = System.currentTimeMillis();

        String inputFilePath = "GcmWebServices_Trace.txt";
        String outputFilePath = "keyCounts.txt";

        try {
            Counter.countKeyOccurrences(inputFilePath, outputFilePath);
        } catch (IOException e) {
            System.out.println("Wrong file name: " + e.getMessage());
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Execution time: " + executionTime + " milliseconds");
    }
}