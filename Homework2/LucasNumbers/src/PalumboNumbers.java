import java.util.Scanner;

/**
 * This class is used to generate and print Palumbo numbers.
 */
public class PalumboNumbers {
    static int og_n;//Original number
    static double startTime;//Start time of the program
    static double prevTime;//Previous time recorded

    static double lastNum;//Last Palumbo number generated
    /**
     * The main method which drives the program.
     * It takes an input 'n' from the user and prints the first 'n' Lucas numbers.
     * It also prints the time taken to generate each number.
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the 'n' of Palumbo numbers you want to print(0, 1, 2, ...): ");
        og_n = sc.nextInt();
        sc.nextLine();
        startTime = System.currentTimeMillis();
        prevTime = System.currentTimeMillis();
        //for loop for testing ratios of consecutive numbers
         
        for(int i = 0; i <= og_n; i++){
            if (i == 0) {
                //System.out.println(0 + ". Time taken: " + (System.currentTimeMillis() - startTime) + "ms");
            }
            else if (i == 1) {
                //System.out.println(1 + ". Time taken: " + (System.currentTimeMillis() - startTime) + "ms");
            }
            else if(i > 1){
            palumboNumbers(2, 2, (int)(i - 1));
            }
        }
        
        //regular call for palumboNumbers
        //palumboNumbers(2, 2, og_n - 2);
        sc.close();
    }/**
     * This method generates and prints Palumbo numbers recursively.
     * It also prints the ratio of the current number to the last number and the ratio of the current time to the previous time.
     * @param n_2 The second last Palumbo number generated.
     * @param n_1 The last Palumbo number generated.
     * @param count The count of remaining numbers to be generated.
     */

    public static void palumboNumbers(double n_2, double n_1, int count){
        if(count == 1){//If count is 1, print the number and time taken
            //ratio prints for the last calculation of each n
            System.out.println("num ratio: " + ((double)(n_2 + n_1) / (double)lastNum));
            System.out.println("time ratio: " + ((double)System.currentTimeMillis() / prevTime));
            lastNum = 3*(n_2 + n_1) - 1;
            prevTime = (double) System.currentTimeMillis();
            
            //print for regular palumbo numbers
            //System.out.println( (3*(n_2 + n_1) - 1) + ". Time taken: " + (System.currentTimeMillis() - startTime) + "ms");
        }
        else{//otherwise call the function recursively
            //print for every palumbo number and its time taken
            //System.out.println("n is: " + (og_n - count) + " value is: " + (3*(n_2 + n_1) - 1) + " time taken: " 
            // + (System.currentTimeMillis() - prevTime) + "ms");
            palumboNumbers(n_1, (3*(n_2 + n_1) - 1), (count - 1));
        }
    }
}
