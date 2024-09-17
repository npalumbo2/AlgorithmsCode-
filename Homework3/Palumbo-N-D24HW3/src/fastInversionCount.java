import java.util.Scanner;

public class fastInversionCount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements:");
        int n = scanner.nextInt();

        int[] input = new int[n];
        int[] temp = new int[n];

        System.out.println("Enter " + n + " numbers:");
        for (int i = 0; i < n; i++) {
            input[i] = scanner.nextInt();
        }

        scanner.close();

        int ivs = mergeSortAndCount(input, temp, 0, n - 1);

        System.out.println("The number of inversions is: " + ivs);
        System.out.print("The sorted array is: ");
        for (int i = 0; i < n; i++) {
            System.out.print(input[i] + " ");
        }
    }

    //This method merges two sorted halves of a subarray (from 'left' to 'mid' and from 'mid' to 'right')
    //and counts the number of inversions during the merge process.
    public static int mergeAndCount(int[] a, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid;
        int k = left;
        int ivs = 0;

        while ((i <= mid - 1) && (j <= right)) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
                ivs += (mid - i);
            }
        }

        while (i <= mid - 1) {
            temp[k++] = a[i++];
        }

        while (j <= right) {
            temp[k++] = a[j++];
        }

        for (i = left; i <= right; i++) {
            a[i] = temp[i];
        }
        
        return ivs;
    }
    
    //Sorts the given array using the merge sort algorithm and counts the number of inversions.
    public static int mergeSortAndCount(int[] arr, int[] temp, int left, int right) {
        int ivs = 0;
        if (right > left) {
            int mid = (right + left) / 2;

            ivs += mergeSortAndCount(arr, temp, left, mid);
            ivs += mergeSortAndCount(arr, temp, mid + 1, right);

            ivs += mergeAndCount(arr, temp, left, mid + 1, right);
        }
        return ivs;
    }
}
