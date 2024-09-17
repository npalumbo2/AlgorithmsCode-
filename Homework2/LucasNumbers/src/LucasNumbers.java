import java.util.Scanner;

/**
 * This class is used to generate and print Lucas numbers.
 */
public class LucasNumbers {
    static int og_n; // Original number
    static double startTime; // Start time of the program
    static double prevTime; // Previous time recorded

    static double lastNum; // Last Lucas number generated

    /**
     * The main method which drives the program.
     * It takes an input 'n' from the user and prints the first 'n' Lucas numbers.
     * It also prints the time taken to generate each number.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the 'n' of Lucas numbers you want to print(0, 1, 2, ...): ");
        og_n = sc.nextInt();
        sc.nextLine();
        startTime = System.currentTimeMillis();
        prevTime = System.currentTimeMillis();
        /* 
        for (int i = 0; i <= og_n; i++) {
            if (i == 0) {
                System.out.println(0 + ". Time taken: " + (System.currentTimeMillis() - startTime) + "ms");
            } else if (i == 1) {
                System.out.println(1 + ". Time taken: " + (System.currentTimeMillis() - startTime) + "ms");
            } else if (i > 1) {
                lucasNumbers(2, 1, i - 1);
            }
        }
        */
        lucasNumbers(2, 1, og_n-2);
        sc.close();
    }

    /**
     * This method generates and prints Lucas numbers recursively.
     * It also prints the ratio of the current number to the last number and the ratio of the current time to the previous time.
     * @param n_2 The second last Lucas number generated.
     * @param n_1 The last Lucas number generated.
     * @param count The count of remaining numbers to be generated.
     */
    public static void lucasNumbers(double n_2, double n_1, int count) {
        if (count == 1) {//If count is 1, print the number and time taken
            //ratio prints for the last calculation of each n
            //System.out.println("num ratio: " + ((double) (n_2 + n_1) / (double) lastNum));
            //System.out.println("time ratio: " + ((double) System.currentTimeMillis() / prevTime));
            lastNum = n_2 + n_1;
            prevTime = (double) System.currentTimeMillis();

            //print for regular palumbo numbers
            System.out.println((n_2 + n_1) + ". Time taken: " + (System.currentTimeMillis() - startTime) + "ms");
        } 
        else {//otherwise call the function recursively
            //print for every lucas number and its time taken
            // System.out.println("n is: " + (og_n - count) + " value is: " + (n_2 + n_1) + " time taken:
            // " + (System.currentTimeMillis() - prevTime) + "ms");
            lucasNumbers(n_1, n_2 + n_1, count - 1);
        }
    }
}