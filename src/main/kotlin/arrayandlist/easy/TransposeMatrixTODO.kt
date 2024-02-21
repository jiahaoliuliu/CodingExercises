package arrayandlist.easy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Transpose Matrix
 *
 * Given a 2D integer array matrix, return the transpose of matrix.
 *
 * The transpose of a matrix is the matrix flipped over its main diagonal, switching the matrix's
 * row and column indices.
 *
 * Example 1:
 *      Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 *      Output: [[1,4,7],[2,5,8],[3,6,9]]
 *
 * Example 2:
 *      Input: matrix = [[1,2,3],[4,5,6]]
 *      Output: [[1,4],[2,5],[3,6]]
 *
 * Constraints:
 *      m == matrix.length
 *      n == matrix[i].length
 *      1 <= m, n <= 1000
 *      1 <= m * n <= 10^5
 *      -10^9 <= matrix[i][j] <= 10^9
 */
abstract class TransposeMatrix {
    abstract fun transpose(matrix: Array<IntArray>): Array<IntArray>

    @Test
    fun test1() {
        // Given
        val matrix = arrayOf(intArrayOf(1,2,3), intArrayOf(4,5,6), intArrayOf(7,8,9))

        // When
        val result = transpose(matrix)

        // Then
        assertEquals(3, result.size)
        assertTrue(intArrayOf(1, 4, 7) contentEquals result[0])
        assertTrue(intArrayOf(2, 5, 8) contentEquals result[1])
        assertTrue(intArrayOf(3, 6, 9) contentEquals result[2])
    }

    @Test
    fun test2() {
        // Given
        val matrix = arrayOf(intArrayOf(1,2,3), intArrayOf(4,5,6))

        // When
        val result = transpose(matrix)

        // Then
        assertEquals(3, result.size)
        assertTrue(intArrayOf(1, 4) contentEquals result[0])
        assertTrue(intArrayOf(2, 5) contentEquals result[1])
        assertTrue(intArrayOf(3, 6) contentEquals result[2])
    }
}

class TransposeMatrixImpl: TransposeMatrix() {

    /**
     * Initial thoughts
     *
     * Transpose a matrix is going through the first item till diagonal and then exchange it
     * with [j, i]
     *
     * for (i in 0 until n)
     *      for (j in 0 until i)
     *          swap(matrix[i][j], matrix[j][i])
     */
    override fun transpose(matrix: Array<IntArray>): Array<IntArray> {
        return matrix
    }

}
