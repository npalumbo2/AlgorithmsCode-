import java.util.Scanner;

public class easyInversionCount {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements (or press enter for default 8):");
        int n = 8; // default length
        if (sc.hasNextInt()) {
            n = sc.nextInt();
        } else {
            sc.nextLine(); 
        }
        
        int[] input = new int[n];
        System.out.println("Enter " + n + " numbers:");
        for (int i = 0; i < n; i++) {
            input[i] = sc.nextInt();
        }

        int ivs = countInversions(input);
        System.out.println("The number of inversions is: " + ivs);
    }

    public static int countInversions(int[] a) {
        int ivs = 0;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    ivs++;
                }
            }
        }
        return ivs;
    }
} 
