package arrayandlist.easy

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.abs

/**
 * Find winner on a tic tac toc game
 *
 * Tic-tac-toe is played by two players A and B on a 3 x 3 grid. The rules of Tic-Tac-Toe are:
 *
 * Players take turns placing characters into empty squares ' '.
 * The first player A always places 'X' characters, while the second player B always places 'O' characters.
 * 'X' and 'O' characters are always placed into empty squares, never on filled ones.
 * The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 * Given a 2D integer array moves where moves[i] = [rowi, coli] indicates that the ith move will be played on
 * grid[rowi][coli]. return the winner of the game if it exists (A or B). In case the game ends in a draw return
 * "Draw". If there are still movements to play return "Pending".
 *
 * You can assume that moves is valid (i.e., it follows the rules of Tic-Tac-Toe), the grid is initially empty,
 * and A will play first.
 *
 * Example 1:
 *      Input: moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
 *      [Row, Column]
 *      Output: "A"
 *      Explanation: A wins, they always play first.
 * | x |   |   |
 * |   | x |   |
 * | o | o | x |
 *
 * Example 2:
 *      Input: moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
 *      Output: "B"
 *      Explanation: B wins.
 * | x | x | o |
 * | x | o |   |
 * | o |   |   |
 *
 * Example 3:             A     B     A     B     A     B     A     B     A
 *      Input: moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
 *      Output: "Draw"
 *      Explanation: The game ends in a draw since there are no moves to make.
 * | x | x | o |
 * | o | o | x |
 * | x | o | x |
 *
 * Constraints:
 *  1 <= moves.length <= 9
 *  moves[i].length == 2
 *  0 <= rowi, coli <= 2
 *  There are no repeated elements on moves.
 *  moves follow the rules of tic tac toe.
 *
 */
class FindWinnerOnATicTacTocGame {

    /**
     * initial thought
     * Start building the steps
     *  A = [moves[0], moves[2], moves[4], moves[6], moves[8]]
     *  B = [moves[1], moves[3], moves[5], moves[7], moves[9]]
     *
     * if (A is winning) return 'A'
     * if (B is winning) return 'B'
     * else return "Draw"
     * -> This solution is too complex. I am using some other solutions
     *  -> https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/solutions/441319/java-python-3-check-rows-columns-and-two-diagonals-w-brief-explanation-and-analysis/
     */
    private fun tictactoe(moves: Array<IntArray>): String {
        // 1. Init the value
        val movementA = mutableListOf<Pair<Int, Int>>()
        val movementB = mutableListOf<Pair<Int, Int>>()

        // 2. Loop
        for (i in moves.indices) {
            val move = Pair(moves[i][0], moves[i][1])
            if (i % 2 == 0) {
                movementA.add(move)
            } else {
                movementB.add(move)
            }
        }

        // Check result
        if (isWinning(movementA)) return "A"
        if (isWinning(movementB)) return "B"
        return if (moves.size == 9) "Draw" else "Pending"
    }

    /**
     * We can build a tree, where each one of the node is a position on the tic-tac-toe.
     *
     * So, starting from the first node, we can check if the path exists.
     * - If so, continue until the node is a leaf
     * - Otherwise, it is not winning
     *
     * Now, the difficult part is that the moves are not sorted. They could be anywhere which it was not before
     * so we need to check all the possible chances
     *
     * 1. Building the tree
     * 2. Loop
     */
    private fun isWinning(moves: List<Pair<Int, Int>>): Boolean {
        return false
    }

    // -------------------------------------------------------
    /**
     * Solution from
     *  https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/solutions/441319/java-python-3-check-rows-columns-and-two-diagonals-w-brief-explanation-and-analysis/
     *
     *  The idea is going through the list and go through all the movements
     *  1. Init
     *  total row = 0
     *  total column = 0
     *  total diagonal = 0
     *  total antidiagonal = 0
     *
     *  Player A has the value 1
     *  Player B has the value 2
     *
     *  2. Loop
     *  Per each moment, add the value of its player to the corresponding row, column, diagonal and/or antidiagonal
     *  Once done, check if the absolute sum of row, column, diagonal or antidiagonal is 3
     *  - If so, return the name of the player
     *
     *  3. Show result
     *  If length(movement) == 9 -> Draw
     *  Otherwise -> Pending
     */
    private fun tictactoeOptimized(moves: Array<IntArray>): String {
        // 1. Init the values
        val rowSum = IntArray(3)
        val columnSum = IntArray(3)
        var diagonal = 0
        var antidiagonal = 0
        var playerValue = 1 // 1 for Player A, -1 for Player B

        // 2. Loop
        for (move in moves) { //[Row, Column]
            // Check if there is 3 players in a row
            rowSum[move[0]] += playerValue
            var possibleWinner = checkWinner(rowSum[move[0]])
            possibleWinner?.let {
                return it
            }

            // Check if there is 3 players in any column
            columnSum[move[1]] += playerValue
            possibleWinner = checkWinner(columnSum[move[1]])
            possibleWinner?.let {
                return it
            }

            // Check for diagonal
            if (move[0] == move[1]) { //[0, 0] [1, 1] [2, 2]
                diagonal += playerValue
                possibleWinner = checkWinner(diagonal)
                possibleWinner?.let {
                    return it
                }
            }

            // Check for antidiagonal
            if (move[0] + move[1] == 2) { // [2, 0] [1, 1] [0, 2]
                antidiagonal += playerValue
                possibleWinner = checkWinner(antidiagonal)
                possibleWinner?.let {
                    return it
                }
            }

            // Reverse the player
            playerValue *= -1
        }

        // 3. Show result
        return if (moves.size == 9) "Draw" else "Pending"
    }

    private fun checkWinner(sum: Int): String? {
        if (sum == 3) return "A"
        if (sum == -3) return "B"
        return null
    }

    @Test
    fun test1() {
        // Given
        val moves = arrayOf(
            intArrayOf(0,0),
            intArrayOf(2,0),
            intArrayOf(1,1),
            intArrayOf(2,1),
            intArrayOf(2,2))

        // When
        val result = tictactoeOptimized(moves)

        // Then
        assertEquals("A", result)
    }

    @Test
    fun test2() {
        // Given
        val moves = arrayOf(
            intArrayOf(0,0),
            intArrayOf(1,1),
            intArrayOf(0,1),
            intArrayOf(0,2),
            intArrayOf(1,0),
            intArrayOf(2,0)
        )

        // When
        val result = tictactoeOptimized(moves)

        // Then
        assertEquals("B", result)
    }

    @Test
    fun test3() {
        // Given
        val moves = arrayOf(
            intArrayOf(0,0),
            intArrayOf(1,1),
            intArrayOf(2,0),
            intArrayOf(1,0),
            intArrayOf(1,2),
            intArrayOf(2,1),
            intArrayOf(0,1),
            intArrayOf(0,2),
            intArrayOf(2,2),
        )

        // When
        val result = tictactoeOptimized(moves)

        // Then
        assertEquals("Draw", result)
    }

    @Test
    fun test4() {
        // Given
        val moves = arrayOf(
            intArrayOf(0,0),
            intArrayOf(0,2),
            intArrayOf(1,0),
            intArrayOf(1,1),
            intArrayOf(2,0),
            intArrayOf(2,1),
        )

        // When
        val result = tictactoeOptimized(moves)

        // Then
        assertEquals("A", result)
    }

    // ----------------------------------------------
    /**
     * The best solution found -> It doesn't work
     */
    private fun tictactoeBest(moves: Array<IntArray>): String {
        val cnt = IntArray(8)
        val size = moves.size

        var i = size - 1
        while (i >= 0) {
            cnt[moves[i][0]]++
            cnt[moves[i][1] + 3]++
            if (moves[i][0] == moves[i][1]) {
                cnt[6]++
            }
            if (moves[i][0] + moves[i][1] == 2) {
                cnt[7]++
            }
            if (cnt[moves[i][0]] == 3 || cnt[moves[i][1] + 3] == 3 || cnt[6] == 3 || cnt[7] == 3) {
                return if (size % 2 == 0) "B" else "A"
            }
            i -= 2
        }
        if (size < 9) {
            return "Pending"
        }
        return "Draw"
    }

    @Test
    fun testBest1() {
        // Given
        val moves = arrayOf(
            intArrayOf(0,0),
            intArrayOf(2,0),
            intArrayOf(1,1),
            intArrayOf(2,1),
            intArrayOf(2,2))

        // When
        val result = tictactoeBest(moves)

        // Then
        assertEquals("A", result)
    }

    @Test
    fun testBest2() {
        // Given
        val moves = arrayOf(
            intArrayOf(0,0),
            intArrayOf(1,1),
            intArrayOf(0,1),
            intArrayOf(0,2),
            intArrayOf(1,0),
            intArrayOf(2,0)
            )

        // When
        val result = tictactoeBest(moves)

        // Then
        assertEquals("B", result)
    }

    @Test
    fun testBest3() {
        // Given
        val moves = arrayOf(
            intArrayOf(0,0),
            intArrayOf(1,1),
            intArrayOf(2,0),
            intArrayOf(1,0),
            intArrayOf(1,2),
            intArrayOf(2,1),
            intArrayOf(0,1),
            intArrayOf(0,2),
            intArrayOf(2,2),
        )

        // When
        val result = tictactoeBest(moves)

        // Then
        assertEquals("Draw", result)
    }


    /**
     * | x |   | o |
     * | x | o |   |
     * | x | o |   |
     */
    @Test
    fun testBest4() {
        // Given
        val moves = arrayOf(
            intArrayOf(0,0),
            intArrayOf(0,2),
            intArrayOf(1,0),
            intArrayOf(1,1),
            intArrayOf(2,0),
            intArrayOf(2,1),
        )

        // When
        val result = tictactoeBest(moves)

        // Then
        assertEquals("A", result)
    }
}