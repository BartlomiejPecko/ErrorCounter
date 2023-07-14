public class Main {
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis(); //starts timer

        String inputFilePath = "GcmWebServices_Trace.txt";
        String outputFilePath = "keyCounts.txt";
        Counter.countKeyOccurrences(inputFilePath, outputFilePath);

        long endTime = System.currentTimeMillis(); //ends timer
        long executionTime = endTime - startTime;

        System.out.println("Execution time: " + executionTime + " milliseconds");
        System.out.println("Saved file to keyCounts.txt");

    }
}