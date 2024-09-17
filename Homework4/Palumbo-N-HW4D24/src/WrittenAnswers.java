public class WrittenAnswers {
/*
 * 1. The ForwardElimination algortihm fails to solve the system of equations given in the problem when attempting 
 * to work with the second colomn. After the first elmimination step, the second row of the matrix is two zeros and a 1
 * The algorithm attempts to use the second 0 as a pivot, which is not possible. Thus the algorithm leaves the last row with
 * multiple coefficents, and is not able to solve the system of equations.
 * The BetterForwardElimination algorithm is able to solve the system of equations given in the problem. The algorithm differs 
 * from the first in that it checks to make sure the pivot it is about to use is the largest in the columb. If it is not, the
 * algorithm swaps the rows to make sure the pivot is the largest. This allows the algorithm to solve the system of equations given.
 * 
 * 2. In the second problem, the BetterForwardElimination algorithm cannot handle the system of equation given. 
 * The third and second row of the matrix are both 0 0 1, which means that the algorithm cannot find a pivot 
 * to use that isnt zero, and is unable to proceed. A solution to this problem would be to add a check to the algorithm
 * that checks if the pivot is zero, and if it is, swaps the row with the row below it. This would allow the algorithm to
 * 
 */
}
