public class MostPreciousPath {
    public static void main(String[] args) {
        int[][] vault = {
                {96, 33, 44, 98, 75, 68, 99, 84},
                {10, 41,  1, 86, 46, 24, 53, 93},
                {83, 97, 94, 27, 65, 51, 30,  7},
                {56, 70, 47, 64, 22, 88, 67, 12},
                {91, 11, 77, 48, 13, 71, 92, 15},
                {32, 59, 17, 25, 31,  4, 16, 63},
                {79,  5, 14, 23, 78, 37, 40, 74},
                {35, 89, 52, 66, 82, 20, 95, 21}};
        
        // Find the most precious path
        MPPResult result = findMostPreciousPath(vault);
        
        // Output the result
        System.out.println("Starting square: " + result.startingSquare);
        System.out.println("Path: " + result.path);
        System.out.println("Total number of gems collected: " + result.totalGems);
        System.out.println("The vault number containing the Arkenstone: " + result.av);
    }
    
    public static MPPResult findMostPreciousPath(int[][] vault) {
        int rows = vault.length;
        int cols = vault[0].length;
        int[][] dp = new int[rows][cols]; // dp array to store max gems until each cell
        int[][] path = new int[rows][cols]; // to store the path taken
        
        // Base case: The last row will have the same values as the vault since that's where we start collecting gems
        for (int i = 0; i < cols; i++) {
            dp[rows - 1][i] = vault[rows - 1][i];
            path[rows - 1][i] = -1; // -1 indicates that we are at the last row
        }
        
        // Build the dp array from bottom to top
        for (int i = rows - 2; i >= 0; i--) {
            for (int j = 0; j < cols; j++) {
                // For each cell, check the possible moves and choose the one with the maximum gems
                int maxGemsFromBelow = dp[i + 1][j]; // Directly below
                int pathFrom = j;
                
                if (j > 0 && dp[i + 1][j - 1] > maxGemsFromBelow) {
                    maxGemsFromBelow = dp[i + 1][j - 1]; // Diagonal left
                    pathFrom = j - 1;
                }
                
                if (j < cols - 1 && dp[i + 1][j + 1] > maxGemsFromBelow) {
                    maxGemsFromBelow = dp[i + 1][j + 1]; // Diagonal right
                    pathFrom = j + 1;
                }
                
                dp[i][j] = vault[i][j] + maxGemsFromBelow;
                path[i][j] = pathFrom;
            }
        }
        
        // Find the starting point with the maximum gems on the top row
        int maxGems = 0;
        int startingPoint = 0;
        for (int i = 0; i < cols; i++) {
            if (dp[0][i] > maxGems) {
                maxGems = dp[0][i];
                startingPoint = i;
            }
        }
        
        // Backtrack to find the path taken to collect the maximum number of gems
        String sb = "";
        int r = 0;
        int c = startingPoint;
        int av = c + 1; 
        while (r < rows) {
            sb += (String.format("(%d,%d) ", r + 1, c + 1)); 
            c = path[r][c];
            r++;
        }
        
        // Return the result
        return new MPPResult(startingPoint + 1, sb.toString(), maxGems, av);
    }
}

class MPPResult {
    int startingSquare;
    String path;
    int totalGems;
    int av;
    
    public MPPResult(int startingSquare, String path, int totalGems, int av) {
        this.startingSquare = startingSquare;
        this.path = path;
        this.totalGems = totalGems;
        this.av = av;
    }
}
