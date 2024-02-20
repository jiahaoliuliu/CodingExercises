package arrayandlist.medium

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Paths on matrix with walls
 *
 * Given a matrix where a cell has the value of 1 if it's a wall and 0 if not, find the number of ways
 * to go from the top-left cell to the bottom-right cell, knowing that it's not possible to pass from a
 * wall and we can only go to the right or to the bottom
 *
 * Example:
 *      input: matrix = [[0, 0, 1, 0, 1],
 *                       [0, 0, 0, 0, 1],
 *                       [0, 0, 1, 0, 0],
 *                       [1, 0, 0, 0, 0]]
 *
 * output: 7
 *      explanation (Drawing with 7 paths)
 *
 * Constraints:
 *      matrix[i][j] can either be 0 or 1
 *      len(matrix) >= 1
 *      len(matrix[i]) >= 1
 */
abstract class PathsOnMatrixWithWalls {

    abstract fun paths(matrix: Array<IntArray>): Int

    @Test
    fun test1() {
        // Given
        val matrix = arrayOf(intArrayOf(0, 0, 1, 0, 1),
                             intArrayOf(0, 0, 0, 0, 1),
                             intArrayOf(0, 0, 1, 0, 0),
                             intArrayOf(1, 0, 0, 0, 0))

        // When
        val result = paths(matrix)

        // Then
        assertEquals(7, result)
    }
}

class PathsOnMatrixWithWallsRecursive: PathsOnMatrixWithWalls() {

    /**
     * Initial thoughts
     *
     * The unique movement allowed is either to the right or to the bottom
     *
     * Subproblem
     *  - Each path(i, j) represents the number of paths from that position to the position (n -1, m -1)
     *
     * General statements
     * - path(i, j) = path(i + 1, j) + path (i, j + 1)
     *
     * Base case
     * - if ((i == n -1) && (j == m -1)) -> We reached to the bottom
     *   path(i, j) = 0
     *
     * Special cases:
     * - if (matrix(i+1, j) == 1 && matrix(i, j+1) == 1) -> We are blocked
     *   path(i, j) = 0
     *
     * - if (matrix(i+1, j) == 1 -> We cannot pass to the bottom
     *   path(i, j) = path(i, j+1)
     *
     * - if (matrix(i, j + 1) == 1 -> We cannot pass to the right
     *   path(i, j) = path(i+1, j)
     *
     * Corner case:
     * - if (i == n -1) -> If it is the last row, we only can go to the right
     *   path(i, j) = path(i, j+1)
     * - if (j == m -1) -> If it is the last column, we only can go to the bottom
     *   path(i, j) = path(i+1, j)
     *
     *   Complexity:
     *   - Time: O(2^(n+m))
     *   - Space: O(n + m)
     */
    override fun paths(matrix: Array<IntArray>): Int {
        return paths(matrix, 0, 0)
    }

    private fun paths(matrix: Array<IntArray>, i: Int, j: Int): Int {
        val n = matrix.size
        val m = matrix[0].size

        if (i == n -1 && j == m -1) {
            return 1
        } else if (i == n - 1 ) {
            return paths(matrix, i, j+1)
        } else if (j == m - 1 ) {
            return paths(matrix, i+1, j)
        } else {
            // Blocking walls
            return if (matrix[i][j+1] == 1 && matrix[i+1][j] == 1) {
                0
            // If there is a wall on the bottom, then just go to the right
            } else if (matrix[i+1][j] == 1) {
                paths(matrix, i, j+1)
            // If there is a wall on the right, then we just go to the bottom
            } else if (matrix[i][j+1] == 1) {
                paths(matrix, i+1, j)
            // General case. The number of ways from one cell is the sum of the
            // number of the ways on the right and on the bottom
            } else {
                paths(matrix, i + 1, j) + paths(matrix, i, j + 1)
            }
        }
    }
}

class PathsOnMatrixWithWallsRecursiveSimpler: PathsOnMatrixWithWalls() {

    /**
     * Initial thoughts
     *
     * A simpler recursive solution
     * We allow the i and j go beyond n
     * In case it happens, the algo returns 0
     * Also in case the matrix[i][j] == 1
     *  then the algo also returns 0
     *
     * Complexity:
     *   - Time: O(2^(n+m))
     *   - Space: O(n + m)
     */
    override fun paths(matrix: Array<IntArray>): Int {
        return paths(matrix, 0, 0)
    }

    private fun paths(matrix: Array<IntArray>, i: Int, j: Int): Int {
        val n = matrix.size
        val m = matrix[0].size

        return if (i == n || j == m || matrix[i][j] == 1) {
            0
        } else if (i == n -1 && j == m -1) { // For the last cell, return 1
            1
        } else { // General case
            paths(matrix, i+1, j) + paths(matrix, i, j+1)
        }
    }
}

class PathsOnMatrixWithWallsMemoization: PathsOnMatrixWithWalls() {

    /**
     * Using a hash table
     * Complexity:
     * - Time: Number of subproblems * complexity of subproblem = nm * O(1) = O(nm)
     * - Space: Call stack size + Size of table = O(n+m) + O(nm) = O(nm)
     */
    override fun paths(matrix: Array<IntArray>): Int {
        return paths(matrix, 0, 0, HashMap())
    }

    private fun paths(matrix: Array<IntArray>, i: Int, j: Int, lookUpTable: HashMap<Pair<Int, Int>, Int>): Int {
        val n = matrix.size
        val m = matrix[0].size

        return if (lookUpTable.containsKey(Pair(i, j))) {
            lookUpTable[Pair(i, j)]!!
        } else if (i == n || j == m || matrix[i][j] == 1) {
            lookUpTable[Pair(i, j)] = 0
            lookUpTable[Pair(i, j)]!!
        } else if (i == n - 1 && j == m - 1) { // For the last cell, return 1
            lookUpTable[Pair(i, j)] = 1
            lookUpTable[Pair(i, j)]!!
        } else { // General case
            lookUpTable[Pair(i, j)] = paths(matrix, i + 1, j, lookUpTable) + paths(matrix, i, j + 1, lookUpTable)
            lookUpTable[Pair(i, j)]!!
        }
    }
}

class PathsOnMatrixWithWallsTabulation: PathsOnMatrixWithWalls() {

    /**
     * paths(i, j) represents the number of paths from the cell (0, 0) to that cell
     *
     * General case
     * - paths(i, j) = paths(i-1, j) + paths(i, j-1)
     *
     * Base case
     * - matrix(0, 0) = 1
     *
     * Corner cases:
     * - First row
     *      - if (i == 0) paths(i, j) = paths(i, j - 1)
     *
     * - First column
     *      - if (j == 0) paths(i, j) = paths(i-1, i)
     *
     * Special cases:
     * - if matrix(i, j) == 1 -> 0
     *
     * Complexity:
     * - Time: O(nm)
     * - Space: O(nm)
     */
    override fun paths(matrix: Array<IntArray>): Int {
        return paths(matrix, 0, 0)
    }

    private fun paths(matrix: Array<IntArray>, i: Int, j: Int): Int {
        val n = matrix.size
        val m = matrix[0].size
        val cache = Array(n){ IntArray(m) }

        // first item (It shouldn't be a wall)
        cache[0][0] = if (matrix[0][0] == 1) 0 else 1

        // first row
        for (j in 1 until m) {
            if (matrix[0][j] == 1) cache[0][j] = 0
            else cache[0][j] = cache[0][j - 1]
        }

        // first column
        for (i in 1 until n) {
            if (matrix[i][0] == 1) cache[i][0] = 0
            else cache[i][0] = cache[i-1][0]
        }

        // General items
        for (i in 1 until n) {
            for (j in 1 until m) {
                if (matrix[i][j] == 1) cache[i][j] = 0
                else cache[i][j] = cache[i - 1][j] + cache[i][j-1]
            }
        }

        return cache[n-1][m-1]
    }
}

class PathsOnMatrixWithWallsTabulationOptimSpace: PathsOnMatrixWithWalls() {

    /**
     * Same case as tabulation, but with better space complexity
     */
    override fun paths(matrix: Array<IntArray>): Int {
        return paths(matrix, 0, 0)
    }

    private fun paths(matrix: Array<IntArray>, i: Int, j: Int): Int {
        val n = matrix.size
        val m = matrix[0].size
        var prevRow = IntArray(m)
        var currentRow = IntArray(m)

        // first item (It shouldn't be a wall)
        prevRow[0] = if (matrix[0][0] == 1) 0 else 1

        // first row
        for (j in 1 until m) {
            if (matrix[0][j] == 1) prevRow[j] = 0
            else prevRow[j] = prevRow[j - 1]
        }

        // General items
        for (i in 1 until n) {
            // first item
            currentRow[0] = if (matrix[i][0] == 1) 0 else prevRow[0]
            // Filling the rest of the items
            for (j in 1 until m) {
                if (matrix[i][j] == 1) currentRow[j] = 0
                else currentRow[j] = prevRow[j] + currentRow[j-1]
            }

            prevRow = currentRow
            currentRow = IntArray(m)

        }

        return prevRow[m - 1]
    }
}
