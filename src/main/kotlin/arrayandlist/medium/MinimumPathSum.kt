package arrayandlist.medium

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.min

/**
 * Minimum path sum aka Minimum cost path
 *
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
 * which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Example 1:
 *      Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 *      Output: 7
 *      Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 *
 * Example 2:
 *      Input: grid = [[1,2,3],[4,5,6]]
 *      Output: 12
 *
 * Constraints:
 *      m == grid.length
 *      n == grid[i].length
 *      1 <= m, n <= 200
 *      0 <= grid[i][j] <= 200
 */
abstract class MinimumPathSum {

    abstract fun minPathSum(grid: Array<IntArray>): Int

    @Test
    fun test1() {
        // Given
        val grid = arrayOf(intArrayOf(1, 3, 1), intArrayOf(1, 5, 1), intArrayOf(4, 2, 1))

        // When
        val result = minPathSum(grid)

        // Then
        assertEquals(7, result)
    }

    @Test
    fun test2() {
        // Given
        val grid = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6))

        // When
        val result = minPathSum(grid)

        // Then
        assertEquals(12, result)
    }

    @Test
    fun test3() {
        // Given
        val grid = arrayOf(intArrayOf(3,  2, 12, 15, 10),
                           intArrayOf(6, 19,  7, 11, 17),
                           intArrayOf(8,  5, 12, 32, 21),
                           intArrayOf(3, 20,  2,  9,  7),
            )

        // When
        val result = minPathSum(grid)

        // Then
        assertEquals(52, result)
    }
}

class MinimumPathSumRecursive: MinimumPathSum() {

    /**
     * General problem
     *
     * We start from the cell (0, 0) and we will go to the cell (m-1, n-1)
     *
     * General statement
     * minCost(i, j) = minimum cost to go from the position i, j to the position m, n
     * minCost(i, j) = matrix(i, j) + min(minCost(i + 1, j), minCost(i, j+1))
     *
     * Special cases:
     * - If we reach to the last column, we only can go down
     * minCost(n-1, j) = matrix(n-1, j) + minCost(n-1, j+1)
     *
     * - if we reach to the last row, we only can go right
     * minCost(i, m-1) = matrix(i, m-1) + minCost(i+1, m-1)
     *
     * Base case: (smallest problem)
     * minCost(n-1, m-1) = Cost itself = matrix(n-1, m-1)
     *
     * Complexity:
     *  Time: O(2^(n + m))
     *  because in the worse case scenario, the algo needs to choose between
     *  minimum of itself and this could go to both row and column
     * Space: O(n + m) or O(n + m - 1)
     *  because in the worse case scenario, there is n + m calls in the calling stack
     */
    override fun minPathSum(grid: Array<IntArray>): Int {
        return minPathSum(grid, 0, 0)
    }

    private fun minPathSum(grid: Array<IntArray>, i: Int, j: Int): Int {
        val n = grid.size
        val m = grid[0].size

        return if (i == n - 1 && j == m - 1) {
            grid[i][j]
        } else if (i == n - 1) {
            grid[i][j] + minPathSum(grid, i, j+1)
        } else if (j == m - 1) {
            grid[i][j] + minPathSum(grid, i+1, j)
        } else {
            grid[i][j] + min(minPathSum(grid, i+1, j), minPathSum(grid, i, j+1))
        }
    }
}

/**
 * For memoization, the result of each cell represents the minimum cost from that
 * cell to the cell (n-1, m-1)
 *
 * The general formula is still valid
 * minCost(i, j) = matrix(i, j) + min(minCost(i + 1, j), minCost(i, j+1))
 *
 */
class MinimumPathSumMemoization: MinimumPathSum() {

    /**
     * Complexity:
     *  Time: Number of subproblems * cost of each subproblem
     *   Number of subproblems: per each cell we only call it once at max = n * m
     *   Cost of each subproblem: O(1) because we have a lookup table
     *  Space: The size of the lookUpTable is n * m because per each item, it
     *   stores the cost, so it is O(nm)
     *
     */
    override fun minPathSum(grid: Array<IntArray>): Int {
        return minPathSum(grid, 0, 0, HashMap())
    }

    private fun minPathSum(grid: Array<IntArray>, i: Int, j: Int, lookUpTable: HashMap<Pair<Int, Int>, Int>): Int {
        if (lookUpTable.containsKey(Pair(i, j))) {
            return lookUpTable[Pair(i, j)]!!
        }

        val n = grid.size
        val m = grid[0].size

        return if (i == n - 1 && j == m - 1) {
            grid[i][j]
        } else if (i == n - 1) {
            lookUpTable[Pair(i, j)] = grid[i][j] + minPathSum(grid, i, j+1, lookUpTable)
            lookUpTable[Pair(i, j)]!!
        } else if (j == m - 1) {
            lookUpTable[Pair(i, j)] = grid[i][j] + minPathSum(grid, i+1, j, lookUpTable)
            lookUpTable[Pair(i, j)]!!
        } else {
            lookUpTable[Pair(i, j)] =
                grid[i][j] + min(
                    minPathSum(grid, i+1, j, lookUpTable),
                    minPathSum(grid, i, j+1, lookUpTable)
                )
            lookUpTable[Pair(i, j)]!!
        }
    }
}

/**
 * In the tabulation (bottom-up), each cell represents the minimum path from
 * the cell(0, 0) to that cell, so the relation is inverse than the memoization
 *
 * In this case, the value of the cell is calculated as the minimum value of the
 * path coming from the top and coming from left.
 *
 * The algo calculates the cheapest path to reach to the current cell from the previous cell,
 * rather than how to go from the current cell
 *
 * In this case, the special cases are the first column and the first row
 */
class MinimumPathSumTabulation: MinimumPathSum() {

    /**
     * Complexity:
     *  Time: (m - 1) * O(1) + (n - 1) * O(1) + (n - 1)(m - 1)*O(1)
     *       = m-1 + n-1 + nm -n - m +1
     *       = nm - 1
     *       = nm
     *  Space: Cache of size (n * m) so it is O(nm)
     */
    override fun minPathSum(grid: Array<IntArray>): Int {
        val n = grid.size
        val m = grid[0].size
        val cache = Array(n) { IntArray(m) }

        // Init the values
        cache[0][0] = grid[0][0]

        // First column (Note the range starts from 1)
        for (i in 1 until n) {
            cache[i][0] = grid[i][0] + cache[i-1][0]
        }

        // First row (Note the range starts from 1)
        for (j in 1 until m) {
            cache[0][j] = grid[0][j] + cache[0][j-1]
        }

        // Rest of cells
        for (i in 1 until n) {
            for (j in 1 until m) {
                cache[i][j] = grid[i][j] + min(cache[i-1][j], cache[i][j-1])
            }
        }

        return cache[n-1][m-1]
    }
}

/**
 * Same as tabulation, but the space is optimized to 2 * m
 *
 */
class MinimumPathSumTabulationOptimSpace: MinimumPathSum() {

    /**
     * Complexity:
     *  Time complexity: Same as normal tabulation. O(nm)
     *  Space complexity: O(m + m) = O(m)
     */
    override fun minPathSum(grid: Array<IntArray>): Int {
        val n = grid.size
        val m = grid[0].size
        var prevRow = IntArray(m)
        var currentRow = IntArray(m)

        // Init the previous row
        prevRow[0] = grid[0][0]
        for (j in 1 until m) {
            prevRow[j] = grid[0][j] + prevRow[j-1]
        }

        // Repeat for all the next n - 1 rows
        for (i in 1 until n) {
            // Fill the first element
            currentRow[0] = prevRow[0] + grid[i][0]
            // Fill with rest of the elements
            for (j in 1 until m) {
                currentRow[j] = grid[i][j] + min(prevRow[j], currentRow[j - 1])
            }

            prevRow = currentRow
            currentRow = IntArray(m)
        }

        return prevRow[m - 1]
    }
}
