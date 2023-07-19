import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the file path: ");
        String inputFilePath = sc.nextLine();
        long startTime = System.currentTimeMillis();


        String outputFilePath = "keyCounts.txt";

        Counter.countKeyOccurrences(inputFilePath, outputFilePath);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Execution time: " + executionTime + " milliseconds");
    }
}