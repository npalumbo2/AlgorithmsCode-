import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MagicSquare {
    static boolean specificSize = false; // If true, the combinations will have a specific size bound
    static boolean sumBounds = false; // If true, the function will be called multiple times for different sum bounds

    static int comboSize = 4; // If specificSize, choose a number of elements in each combination
    public static void main(String[] args) {
        int[] magicSquare = {1, 14, 14, 4, 11, 7, 6, 9, 8, 10, 10, 5, 13, 2, 3, 15};
        int largestSum = 0;
        for (int i = 0; i < magicSquare.length; i++) {
            largestSum += magicSquare[i];
        }
        System.out.println("The largest sum of the magic square is " + largestSum); //132
        int targetSum = 33; // target sum of the combinations
        
        int lowBound = 0;
        int highBound = largestSum;
        if(sumBounds) { // If you want a range of sums
            for (int i = lowBound; i <= highBound; i++) {
                List<List<Integer>> combinations = findCombinations(magicSquare, i);
                System.out.println("There are " + combinations.size() + " combinations that add up to " + i);
                // The sum with the most combinations is 66 with 1364 combinations
            }
        }else if(!specificSize){ // If the combinations do not have a specific size bound
            List<List<Integer>> combinations = findCombinations(magicSquare, targetSum);
            for (List<Integer> combination : combinations) {
                System.out.println(combination);
            }
            System.out.println("There are " + combinations.size() + " combinations that add up to " + targetSum);
        }
        else{ // If the combination size is specified, find combinations of that size
            List<List<Integer>> combinations = findCombinations(magicSquare, targetSum);
            for (List<Integer> combination : combinations) {
                System.out.println(combination);
            }
            System.out.println("There are " + combinations.size() + " combinations of " + comboSize + " numbers that add up to " + targetSum);
        }
    }

    private static List<List<Integer>> findCombinations(int[] squareCombo, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        // Sort the array to handle duplicates more effectively in the combination logic
        Arrays.sort(squareCombo);
        backtrack(result, new ArrayList<>(), targetSum, 0, squareCombo);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> current, int remain, int start,
            int[] squareCombo) {
        if (specificSize) {
            if (remain == 0 && current.size() == comboSize) {
                result.add(new ArrayList<>(current));
                return;
            }
        } else if (remain == 0) {
            result.add(new ArrayList<>(current));
            return;
        } else {
            for (int i = start; i < squareCombo.length; i++) {
                // If the current number is greater than the remaining sum needed, break the
                // loop since the array is sorted
                if (squareCombo[i] > remain)
                    break;

                current.add(squareCombo[i]);
                // Call backtrack with i + 1 to move to the next element and avoid reusing the
                // same element
                backtrack(result, current, remain - squareCombo[i], i + 1, squareCombo); // Allow the same element to be
                                                                                         // used in different
                                                                                         // combinations
                current.remove(current.size() - 1); // Backtrack
            }
        }
    }
}
