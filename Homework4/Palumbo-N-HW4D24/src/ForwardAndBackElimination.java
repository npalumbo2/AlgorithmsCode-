 public class ForwardAndBackElimination {
        public static void main(String[] args) {
            double[][] A = {
                {1,  1, 1,  1,  1,  1,  1,  1, 1,  1, 1, 1},
                {1,  1, 0,  0,  0,  0,  0,  0, 0,  0, 0, 0},
                {0,  0, 1,  1,  0,  0,  0,  0, 0,  0, 0, 0},
                {0,  0, 0,  0,  1,  1,  0,  0, 0,  0, 0, 0},
                {0,  0, 0,  0,  0,  0,  1,  1, 0,  0, 0, 0},
                {0,  0, 0,  0,  0,  0,  0,  0, 1,  1, 0, 0},
                {1,  0, 0,  0,  0,  0,  0,  0, 0,  0, 0, 1},
                {0,  0, 1,  0,  0,  0,  0,  0, 0,  1, 0, 0},
                {0,  0, 0,  0,  0,  4, -3,  0, 0,  0, 0, 0},
                {0,  0, 0,  3, -2,  0,  0,  0, 0,  0, 0, 0}, 
                {0,  0, 0,  1,  0,  0,  0,  0, 1, -1, 0, 0},
                {1, -1, 1, -1,  1, -1,  1, -1, 1, -1, 1,-1} 
            };
            double[] b = {364, 4, 16, 36, 64, 100, 79, 61, 0, 0, 0, -42};
    
            double[] solution = gaussJordanElimination(A, b);
            for (int i = 0; i < solution.length; i++) {
                System.out.println("x" + (i+1) + " = " + (int) solution[i]);
            }
        }
    
        public static double[] gaussJordanElimination(double[][] A, double[] b) {
            int n = A.length;
    
            // Append b to A as the last column
            double[][] system = new double[n][n + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    system[i][j] = A[i][j];
                }
                system[i][n] = b[i];
            }
    
            // Perform Gauss-Jordan elimination
            for (int i = 0; i < n; i++) {
                // Find the row with the maximum absolute value for pivoting
                int pivotRow = i;
                for (int j = i + 1; j < n; j++) {
                    if (Math.abs(system[j][i]) > Math.abs(system[pivotRow][i])) {
                        pivotRow = j;
                    }
                }
    
                // Swap the pivot row with the current row
                double[] temp = system[i];
                system[i] = system[pivotRow];
                system[pivotRow] = temp;
    
                //Normalize the pivot row
                double pivot = system[i][i];
                for (int k = 0; k <= n; k++) {
                    system[i][k] /= pivot;
                }
    
                //Eliminate non-zero numbers above and below the pivot
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        double factor = system[j][i];
                        for (int k = i; k <= n; k++) {
                            system[j][k] -= factor * system[i][k];
                        }
                    }
                }
            }
            double[] solution = new double[n];
            for (int i = 0; i < n; i++) {
                solution[i] = system[i][n];
            }
            return solution;
        }
    }
    