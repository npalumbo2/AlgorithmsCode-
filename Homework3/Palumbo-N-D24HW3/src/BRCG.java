import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class BRCG {
    
    public static void main(String[] args) {
        int order = 7; // nth order, 7 for extra credit case
        //names for each position
        HashMap<Integer, String> clowns = new HashMap<>();
        clowns.put(0, "Giggles");
        clowns.put(1, "Fitz");
        clowns.put(2, "Enzo");
        clowns.put(3, "Doofus");
        clowns.put(4, "Crunchy");
        clowns.put(5, "Boxo");
        clowns.put(6, "Axel");

        // Print the table headers
        System.out.println("Event | Gray Code | Bike State                                         | Action");
        System.out.println("------|-----------|----------------------------------------------------|---------------------");

        List<Integer> prevGrayCode = toBinaryList(0, order);
        for (int i = 0; i < Math.pow(2, order); i++) {
            int grayCode = toGrayCode(i);
            List<Integer> currGrayCode = toBinaryList(grayCode, order);

            StringBuilder bikeState = new StringBuilder();
            // Instead of appending bits, append passenger names based on bits
            for (int j = 0; j < currGrayCode.size(); j++) {
                if (currGrayCode.get(j) == 1) {
                    if (bikeState.length() > 0) bikeState.append(", "); // Add comma for readability
                    bikeState.append(clowns.get(j));
                }
            }

            // Determine the action (who joins or leaves)
            String action = "";
            for (int j = 0; j < order; j++) {
                if (!currGrayCode.get(j).equals(prevGrayCode.get(j))) {
                    if (currGrayCode.get(j) == 1) {
                        action = clowns.get(j) + " joins";
                    } else {
                        action = clowns.get(j) + " leaves";
                    }
                    break; // Exit after finding the first change
                }
            }

            // Build the binary string for the Gray Code column
            String grayCodePrint = "";
            for (Integer bit : currGrayCode) {
                grayCodePrint += bit;
            }

            // Print the current event
            System.out.printf("%5d | %-9s | %-50s | %s%n", i, grayCodePrint, bikeState.length() == 0 ? "Empty" : bikeState, action.isEmpty() ? "Spotlight" : action);

            prevGrayCode = new ArrayList<>(currGrayCode);
        }
    }
    // Method to convert an integer to its nth order Gray code
    public static int toGrayCode(int n) {
        return n ^ (n >> 1);
    }

    // Convert Gray code to a list of integers
    public static List<Integer> toBinaryList(int gc, int order) {
        List<Integer> binaryList = new ArrayList<>(order);
        for (int i = order - 1; i >= 0; i--) {
            int bit = (gc >> i) & 1;
            binaryList.add(bit);
        }
        return binaryList;
    }
}
