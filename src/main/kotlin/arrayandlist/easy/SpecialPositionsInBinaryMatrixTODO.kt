package arrayandlist.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Special position in Matrix
 * Given an m x n binary matrix mat, return the number of special positions in mat.
 *
 * A position (i, j) is called special if mat[i][j] == 1 and all other elements in row i
 * and column j are 0 (rows and columns are 0-indexed).
 *
 * Example 1:
 *      Input: mat = [[1,0,0],[0,0,1],[1,0,0]]
 *      Output: 1
 *      Explanation: (1, 2) is a special position because mat[1][2] == 1 and all other elements in row 1 and column 2 are 0.
 *
 * Example 2:
 *      Input: mat = [[1,0,0],[0,1,0],[0,0,1]]
 *      Output: 3
 *      Explanation: (0, 0), (1, 1) and (2, 2) are special positions.
 *
 * Constraints:
 *      m == mat.length
 *      n == mat[i].length
 *      1 <= m, n <= 100
 * mat[i][j] is either 0 or 1.
 *
 */
abstract class SpecialPositionsInBinaryMatrix {

    abstract fun numSpecial(mat: Array<IntArray>): Int

    @Test
    fun test1() {
        // Given
        val mat = arrayOf(intArrayOf(1,0,0), intArrayOf(0,0,1), intArrayOf(1,0,0))

        // When
        val result = numSpecial(mat)

        // Then
        assertEquals(1, result)
    }

    @Test
    fun test2() {
        // Given
        val mat = arrayOf(intArrayOf(1,0,0), intArrayOf(0,1,0), intArrayOf(0,0,1))

        // When
        val result = numSpecial(mat)

        // Then
        assertEquals(3, result)
    }
}

class SpecialPositionsInBinaryMatrixImpl: SpecialPositionsInBinaryMatrix() {

    /**
     * Initial thoughts
     *
     * Given it is a binary matrix, to avoid extra calculations, for any given row
     * or any given column, if there is only 1 cell is 1 and the rest is 0, then
     * the sum of all the cells will be 1.
     *
     * 1. Create an int array where each element is the sum of each row
     * 2. Create an int array where each element is the sum of each column
     * 3. Given each element in the matrix, if it is 1, check the sum of the elements
     * in that row and in that column
     *  - If so, add 1 to the result
     *
     * 4. Return result
     *
     * Complexity:
     * - Time: O(m * n)
     * - Space: O(m + n)
     */
    override fun numSpecial(mat: Array<IntArray>): Int {
        return -1
    }

}