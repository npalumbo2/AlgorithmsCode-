public class QueensGame {
    public static int count;
    public static void main(String[] args) {
        printQueens(nextLegalPosition(new int[]{1, 6, 8, 3, 5, 0, 0, 0}, 8));
        printQueens(nextLegalPosition(new int[]{1, 6, 8, 3, 7, 0, 0, 0}, 8));
        printQueens(nextLegalPosition(new int[]{1, 6, 8, 3, 7, 4, 2, 5}, 8));
        for(int i = 4; i <= 14;i++){
            int[] queens = new int[i];
            queens[0] = 1;
            for(int j = 1; j < i; j++){
                queens[j] = 0;
            }
            printQueens(nextFullSolution(queens, i));
        }
        for(int i = 4; i <= 12;i++){
            int[] queens = new int[i];
            queens[0] = 1;
            for(int j = 1; j < i; j++){
                queens[j] = 0;
            }
            countSolutions(queens, i);
        }
    }
    public static boolean isLegalPosition(int[] queens, int n){
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                if(queens[i] != 0 && queens[j] != 0){
                    if(queens[i] == queens[j] || Math.abs(queens[i] - queens[j]) == Math.abs(i - j)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public static void printQueens(int[] queens){
        for(int i = 0; i < queens.length; i++){
            System.out.print(queens[i] + " ,");
        }
        System.out.println();  
    }
    public static int[] nextLegalPosition(int[]queens, int n){
        int index = -1;
        for(int i = n-1; i >= 0; i--){
            if(queens[i] != 0){
                index = i;
                break;
            }
        }
        if(index == -1){return null;}
        if(!isLegalPosition(queens, n) && queens[index] < n){
            queens[index]++;
        }
        else if(isLegalPosition(queens, n) && index < n-1){
            index++;
            queens[index]++;
        }
        else {
            queens[index] = 0;
            index--;
            if (index < 0) { 
                return null;
            }
            if (queens[index] < n) {
                queens[index]++;
            } else {
                while (index >= 0 && queens[index] == n) { 
                    queens[index] = 0;
                    index--;
                }
                if (index < 0) { 
                    return null;
                }
                queens[index]++;
            }
        }
        
        if(isLegalPosition(queens, n)){return queens;}
        else{return nextLegalPosition(queens, n);}
    }
    public static int[] nextFullSolution(int[] queens, int n) {
        if (queens == null) {
            return null;
        }
        queens = nextLegalPosition(queens, n); 
        while (queens != null && queens[n-1] == 0) {
            queens = nextLegalPosition(queens, n);
        }
        return queens;
    }
    public static void countSolutions(int[] queens, int n) {
        count = 0;
        int[] solution = nextFullSolution(queens.clone(), n);
        while (solution != null) {
            count++;
            int i = n - 1;
            solution[i]++;
            while (i > 0 && solution[i] > n) {
                solution[i] = 1;
                i--;
                solution[i]++;
            }
            if (solution[0] > n) {
                break; 
            }
            solution = nextFullSolution(solution, n);
        }
        System.out.println("Number of solutions for " + n + " queens: " + count);
    }
    
}