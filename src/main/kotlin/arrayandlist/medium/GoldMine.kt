package arrayandlist.medium

import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Integer.max

/**
 * Gold Mine
 *
 * Given a mine of n rows and m columns where mine[i][j] represents the amount of gold that is present
 * there, we want to enter from the top of the mine and take as much gold as possible when exiting from
 * the bottom, knowing that we can only move to the bottom, to the bottom-left, or to the bottom-right.
 * We can exit from anywhere from the last row.
 *
 * Example:
 *      input:
 *      mine = [
 *          [3, 2, 12, 15, 10],
 *          [6, 19, 7, 11, 17],
 *          [8, 5, 12, 32, 21],
 *          [3, 20, 2,  9, 7]]
 *
 * output: 73
 * explanation: 15+17+32+9 = 73
 *
 * Constraints:
 *      len(matrix) >= 1
 *      len(matrix[i]) >= 1
 *      matrix[i][j] >= 0
 *
 */
abstract class GoldMine {

    abstract fun gold(mine: Array<IntArray>): Int

    @Test
    fun test1() {
        // Given
        val mine = arrayOf(
            intArrayOf(3, 2, 12, 15, 10),
            intArrayOf(6, 19, 7, 11, 17),
            intArrayOf(8, 5, 12, 32, 21),
            intArrayOf(3, 20, 2,  9, 7),
        )

        // When
        val result = gold(mine)

        // Then
        assertEquals(73, result)
    }
}

class GoldMineRecursive: GoldMine() {

    /**
     * Initial thoughts
     * 1. Using the example to check for specific case
     * https://excalidraw.com/#room=060ad4bd11ac1a26e567,OOZaWnM9D9PTGVKCgWcW3g
     *
     * 2. Draw the DAG
     * https://excalidraw.com/#room=fc5c0178700a7c0d28db,0nDcRCDVSabXh-_9lzXmdA
     *
     * 3. Definition
     * gold(i, j) = maximum number of golds the person can get from the position (i, j) to the
     * position (n - 1, m-1)
     *
     * Since the person can go to bottom-left, bottom, or bottom-right. it is
     *
     * General case
     *  gold(i, j) = mine(i, j) + max(gold(i+1, j-1), gold(i+1, j), gold(i+1, j+1)
     *
     * Special cases
     * - exit (Bottom)
     * if (i == n) return 0
     *
     * - First column
     * The person can only go to bottom or go to bottom-right
     *  gold(i, j) = if (i == 0) then = mine(i, j) + max(gold(i+1,j), gold(i+1, j+1))
     *
     * - Last column
     * The person can only go to bottom or go to bottom-left
     *  gold(i, j) = if (j == n -1) then = mine(i, j) + max(gold(i+1, j-1), gold(i+1, j))
     *
     *  If we establish border from the left and from the right
     *  gold(i, j) = if (j == -1 or j == m) return 0
     *
     *  How to solve the case when the user can enter from any point on the top?
     *  - Have a variable initialized to 0, to iterate through all the elements on the top
     *
     * Complexity:
     *  Time: The algo moves 1 row per time, every time there is 3 possible calls, so it is
     *      O(3^n). The algo does it per each m, so it is O(m * 3^n)
     *  Space: The call stack will go from the position 0 to the position n +1
     *      O(n+1) = O(n)
     */
    override fun gold(mine: Array<IntArray>): Int {
        var max = 0

        for (j in 0 until mine[0].size) {
            max = max(max, gold(mine, 0, j))
        }

        return max
    }

    private fun gold(mine: Array<IntArray>, i: Int, j: Int): Int {
        val n = mine.size
        val m = mine[0].size

        // If the person reached to the bottom of the matrix
        if (i == n) return 0

        // If the person reached to the left border or right border
        if (j < 0 || j == m) return 0

        return mine[i][j] + max(
            max(gold(mine, i + 1, j - 1),
                gold(mine, i + 1, j)
            ),
                gold(mine, i + 1, j + 1)
        )
    }
}

class GoldMineMemoization: GoldMine() {

    /**
     * LookUp table
     * - Size: m * n
     * - Key: Pair(i, j)
     * - Value: the max gold mine from point (i, j) to the point (n-1, m-1)
     *
     * Complexity:
     *  Time: Number of subproblems * cost of each one of them = n * m * O(1) = O(nm)
     *  Space: Call stack + lookup table = O(n) + O(nm) = O(nm)
     */
    override fun gold(mine: Array<IntArray>): Int {
        var max = 0

        val lookUpTable = HashMap<Pair<Int, Int>, Int>()

        for (j in 0 until mine[0].size) {
            max = max(max, gold(mine, 0, j, lookUpTable))
        }

        return max
    }

    private fun gold(mine: Array<IntArray>, i: Int, j: Int, lookUpTable: HashMap<Pair<Int, Int>, Int>): Int {
        if (lookUpTable.containsKey(Pair(i, j))) {
            return lookUpTable[Pair(i, j)]!!
        }

        val n = mine.size
        val m = mine[0].size

        // If the person reached to the bottom of the matrix
        if (i == n) return 0

        // If the person reached to the left border or right border
        if (j < 0 || j == m) return 0

        lookUpTable[Pair(i, j)] = mine[i][j] + max(
            max(gold(mine, i + 1, j - 1, lookUpTable),
                gold(mine, i + 1, j, lookUpTable)
            ),
            gold(mine, i + 1, j + 1, lookUpTable)
        )

        return lookUpTable[Pair(i, j)]!!
    }
}

class GoldMineTabulation: GoldMine() {

    /**
     * Reversing the DAG
     * https://excalidraw.com/#room=51396e7cd481d8f6539a,8bTgj3_--JODFthYfqAD1w
     *
     * Definition:
     *  gold(i, j) = maximum gold that we can get by stopping at the cell(i, j)
     *
     * Generalization:
     *  gold(i, j) = mine(i, j) + max( gold(i -1, j -1), gold(i-1, j), gold(i-1, j+1))
     *
     * Corner cases:
     * - Borders
     *  - Top: the basic value of the mine
     *  - Left: if (j < 0) return 0
     *  - Right: If (j == m) return 0
     *
     * Complexity:
     * - Time: O(n * (m+1)) = O(nm)
     * - Space: O(n * (m+2)) = O(nm)
     */
    override fun gold(mine: Array<IntArray>): Int {
        val n = mine.size
        val m = mine[0].size

        val cache = Array(mine.size) { IntArray(mine[0].size + 2)}
        // First row
        for (j in 1 until m + 1) {
            cache[0][j] = mine[0][j-1]
        }

        // By default, all the content inside of the int array is zero
        for (i in 1 until n) {
            for (j in 1 until m + 1) {
                cache[i][j] = mine[i][j - 1] +
                        max(
                            cache[i-1][j-1],
                        max(
                            cache[i-1][j],
                            cache[i-1][j+1]
                            ),
                        )
            }
        }

        // Once the cache is filled, since we can exit from any of column on the last row
        // we need to find the maximum of them
        var max = 0
        for (j in 1 until m + 1) {
            max = max(max, cache[n-1][j])
        }

        return max
    }
}

class GoldMineTabulationOptimSpace: GoldMine() {

    /**
     * Using two rows of size m + 2
     *
     * Complexity:
     * - Time: O(n * (m+1)) = O(nm)
     * - Space: O(2 * (m + 1)) = O(m)
     */
    override fun gold(mine: Array<IntArray>): Int {
        val n = mine.size
        val m = mine[0].size

        var prevRow = IntArray(m + 2)
        var currentRow = IntArray(m + 2)

        // First row
        for (j in 1 until m + 1) {
            prevRow[j] = mine[0][j-1]
        }

        // By default, all the content inside of the int array is zero
        for (i in 1 until n) {
            for (j in 1 until m + 1) {
                currentRow[j] = mine[i][j - 1] +
                        max(
                            prevRow[j-1],
                            max(
                                prevRow[j],
                                prevRow[j+1]
                            ),
                        )
            }

            prevRow = currentRow
            currentRow = IntArray(m + 2)
        }

        // Once the cache is filled, since we can exit from any of column on the last row
        // we need to find the maximum of them
        var max = 0
        for (j in 1 until m + 1) {
            max = max(max, prevRow[j])
        }

        return max
    }
}