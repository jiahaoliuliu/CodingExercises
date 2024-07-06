package arrayandlist.medium

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Subsets that sums up to K
 *
 * Given an array arr of strictly positive integers, and an integer k, create a function that returns the number
 * of subsets of arr that sum up to k.
 *
 * Example 1:
 *      Input: arr = [1, 2, 3, 1], k = 4
 *      Output: 3
 *      Explanation: subsets that sum up to k are [1, 2, 1], [1, 3], and [3, 1]
 *
 * Example 2:
 *      Input: arr = [1, 2, 3, 1, 4], k = 6
 *      Output: 4
 *      Explanation: subsets that sum up to k are [1, 2, 3], [1, 1, 4], [2, 3, 1], and [2, 4]
 *
 * Example 3:
 *      Input: arr = [2, 4, 2, 6, 8], k = 7
 *      Output: 0
 *      Explanation: there are no subsets that sum up to k
 *
 * Check: This problem is not in Leet code. It is possible one of the programs that is in the dynamic programming
 * course in Udemy
 *
 */
abstract class SubsetsThatSumsUpToK {

    abstract fun sumsToK(nums: IntArray, k: Int): Int

    @Test
    fun test1() {
        // Given
        val list = intArrayOf(1, 2, 3, 1)
        val k = 4

        // When
        val result = sumsToK(list, k)

        // Then
        assertEquals(3, result)
    }

    @Test
    fun test2() {
        // Given
        val list = intArrayOf(1, 2, 3, 1, 4)
        val k = 6

        // When
        val result = sumsToK(list, k)

        // Then
        assertEquals(4, result)
    }

    @Test
    fun test3() {
        val list = intArrayOf(2, 4, 2, 6, 8)
        val k = 7

        // When
        val result = sumsToK(list, k)

        // Then
        assertEquals(0, result)
    }
}

class SubsetsThatSumsUpToKRecursive : SubsetsThatSumsUpToK() {

    /**
     * This is a typical dynamic programming problem
     * Each time the algo can choose between use the current number or skip it
     * 1. Decomposition
     * Nums will remain
     * k will remain
     * The algo needs to go through each on of the elements, so i is needed
     * Then algo needs to remember what was the last sum, so sum is needed
     */
    override fun sumsToK(nums: IntArray, k: Int): Int {
        return sumsToK(nums, k, 0, 0)
    }

    /**
     * Return value
     * Since the previous method is asking for the number of possible combinations, the result could be
     * - 0 if the sum exceeds to k. This is because all the number are positive. Once the current sum
     * is bigger than k, there is not need to continue
     * - 1 if the sum is exactly the k.
     *
     * sumsToK(nums, k, nextPos, sumSoFar) = number of subsets that sums to k from the position 0 to
     * position (nextPos - 1)
     *
     * Complexity:
     * - Time: O(2^n) because every time there are two choices
     * - Space: O(n) because in the maximum, the algo need to have n recursive calls
     */
    private fun sumsToK(nums: IntArray, k: Int, nextPos: Int, sumSoFar: Int): Int {
        // If the sum is bigger than the k, there is not need to continue
        // Or if the algo has used all the numbers
        if (sumSoFar > k) {
            return 0
        }

        if (sumSoFar == k) {
            return 1
        }

        if (nextPos == nums.size) {
            return 0
        }

        // 2 options. Either use the current number or either skip the current number
        return sumsToK(nums, k, nextPos + 1, sumSoFar) +
                sumsToK(nums, k, nextPos + 1, sumSoFar + nums[nextPos])
    }
}

class SubsetsThatSumsUpToKMemoization: SubsetsThatSumsUpToK() {

    /**
     * Memoization using a lookup table (hash)
     * - Key: current position and current sum
     * - Value: The number of possible subsets that sums up to k
     */
    override fun sumsToK(nums: IntArray, k: Int): Int {
        return sumsToK(nums, k, 0, 0, HashMap())
    }

    private fun sumsToK(nums: IntArray, k: Int, nextPos: Int, sumSoFar: Int, lookUpTable: HashMap<Pair<Int, Int>, Int>): Int {
        val key = Pair(nextPos, sumSoFar)
        if (lookUpTable.containsKey(key)) {
            return lookUpTable[key]!!
        }

        if (sumSoFar > k) {
            lookUpTable[key] = 0
            return lookUpTable[key]!!
        }

        if (sumSoFar == k) {
            lookUpTable[key] = 1
            return lookUpTable[key]!!
        }

        if (nextPos == nums.size) {
            lookUpTable[key] = 0
            return lookUpTable[key]!!
        }

        lookUpTable[key] = sumsToK(nums, k, nextPos + 1, sumSoFar, lookUpTable) +
                sumsToK(nums, k, nextPos + 1, sumSoFar + nums[nextPos], lookUpTable)

        return lookUpTable[key]!!
    }
}

class SubsetsThatSumsUpToKTabulation: SubsetsThatSumsUpToK() {

    /**
     *
     * sumsToK(nums, k, nextPos, sumSoFar) = number of subsets that sums to k from the position
     * (nextPos - 1) to the last position
     *
     * Create a matrix of (m+1, k+1)
     * - Where m = number of elements
     * - Where n = k + 1
     * Base case: The last position
     * Special cases
     * - Matrix[m+1, j] = 0 where j from 0 to k-1
     * - Matrix[i, k] = 1, where i from 0 to m+1
     *
     * General cases (From m+1, k+1 till 0, 0
     * nums[1, 2, 3, 1]
     * nums[0] = 1
     * nums[1] = 2
     * nums[2] = 3
     * nums[3] = 1
     *
     * - Matrix[3, 0] = Matrix[4, 0] + Matrix[4, 1]
     *                         ^ = i + 1      ^ = i + 1
     *                            ^ = j          ^ = j + nums[i]
     *
     * - Matrix[3, 3] = Matrix[4, 3] + Matrix[4, 4]
     *                         ^ = i + 1      ^ = i + 1
     *                            ^ = j          ^ = j + nums[i]
     *
     * - Matrix[2, 2] = Matrix[3, 2] + Matrix[3, 5]
     *                         ^ = i + 1     ^ = i + 1
     *                           ^ = j           ^ = j + nums[i]
     *
     * General case
     * - Matrix[i, j] = Matrix[i+1, j] + Matrix[i + 1, j + nums[i]]
     * if (j + nums[i]) > k --> Result = 0
     */
    override fun sumsToK(nums: IntArray, k: Int): Int {
        // Init the values -> i = current pos, j = sum so far,
        val matrix = Array(nums.size + 1){ IntArray( k + 1) }
        // Init the base case
        matrix[nums.size][k] = 1

        for (i in 0 until nums.size) {
            matrix[i][k] = 1
        }

        for (j in 0 until k) {
            matrix[nums.size][j] = 0
        }


        for (i in (0 until nums.size).reversed()) {
            print(i)
            for (j in (0 until k).reversed()) {
                print(", $j")
                val jSecondLoop = j + nums[i]
                if (jSecondLoop >= matrix.size) {
                    matrix[i][j] = matrix[i+1][j]
                } else {
                    val part1 = matrix[i+1][j]
                    print(", $part1")
                    val part2 = matrix[i+1][jSecondLoop]
                    print(", $part2")
                    matrix[i][j] = matrix[i+1][j] + matrix[i+1][jSecondLoop]
                    println(", ${matrix[i][j]}")
                }
            }
        }

        return matrix[0][0]
    }

}