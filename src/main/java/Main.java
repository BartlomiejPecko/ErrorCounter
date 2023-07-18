public class Main {
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        String inputFilePath = "GcmWebServices_Trace.txt";
        String outputFilePath = "keyCounts.txt";

        Counter.countKeyOccurrences(inputFilePath, outputFilePath);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Execution time: " + executionTime + " milliseconds");
    }
}