package arrayandlist.medium

import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Given an array, positive and negative. Find the longest array which the sum of all the items is zero
 *
 * Example 1:  [5, 1, 0, 2, -3, 4, 2]
 *      - Output [1, 0, 2, -3]
 *
 * Example 2: [1, 2, 3, 4, 5, 6, 7]
 *      - Output []
 *
 * Example 3: [1, 9, -1, -2, 3, 0, 4, -2, -2, 3, 4]
 *      - Output [-1, -2, 3, 0, 4, -2, -2]
 *
 * Example 4: [10, 3, 4, -2, 4, -5, 8, 9, -12, 6]
 *      - Output [4, -2, 4, -5, 8, 9, -12]
 */
abstract class ZeroSum {

    abstract fun sumZero(nums: IntArray): IntArray

    @Test
    fun testCornerCase_AllPossitive() {
        // Given
        val nums = intArrayOf(1, 2, 3, 4, 5, 6, 7)

        // When
        val result = sumZero(nums)

        // Then
        assertTrue(result.isEmpty())
    }

    @Test
    fun test1() {
        // Given
        val nums = intArrayOf(5, 1, 0, 2, -3, 4, 2)

        // When
        val result = sumZero(nums)

        // Then
        assertTrue(intArrayOf(1, 0, 2, -3) contentEquals result)
    }

    @Test
    fun test2() {
        // Given
        val nums = intArrayOf(1, 9, -1, -2, 3, 0, 4, -2, -2, 3, 4)

        // When
        val result = sumZero(nums)

        // Then
        assertTrue(intArrayOf(-1, -2, 3, 0, 4, -2, -2) contentEquals result)
    }

    @Test
    fun test3() {
        // Given
        val nums = intArrayOf(10, 3, 4, 0, -4, -5, 8, 9, -12, 6)

        // When
        val result = sumZero(nums)

        // Then
        assertTrue(intArrayOf(4, 0, -4, -5, 8, 9, -12) contentEquals result)
    }
}

class ZeroSumImpl: ZeroSum() {

    /**
     * Initial thoughts
     *
     * Observation
     * - The list of numbers are not related, they are random
     * - The result needs the list of numbers in the same order, so we cannot sort them
     *
     * Brute force:
     * - Going from item 0 to the last item
     *      - Increasing the windows size from right
     *      - add 1 item each time
     *      - If they sum zero, check the length
     *          - if it is longer than existing one, then add result
     * - weak point
     *      - We are doing sum all the time
     * - optimization
     *      - What if we pre-calculate the sums before
     *
     * Example 1:  [5, 1, 0, 2, -3, 4, 2]
     *      - Output [1, 0, 2, -3]
     *
     * Giving 5
     *  -> It is not zero
     *  -> 5 + 1 = 6 != 0
     *  -> 6 + 0 = 6 != 0
     *  -> 6 + 2 = 8 != 0
     *  -> 8 - 3 = 5 != 0
     *  -> 5 + 4 = 9 != 0
     *  -> 9 + 2 = 11 != 0
     *
     * Given 1
     * -> It is not zero
     * -> 1 + 0 = 1 != 0
     * -> 1 + 2 = 3 != 0
     * -> 3 - 3 = 0 == 0 Size from [1, 0, 2, -3]
     * -> 0 + 4 = 4 != 0
     * -> 4 + 2 = 6 != 0
     *
     * Use prefix sum and a hashmap
     * The prefix sum will have the recorded the up and down on the graph as the numbers
     * Then with the hashmap, if some number repeats, we can check the distance between
     * the current number and the next number, then the position between them is the substring
     *
     */
    override fun sumZero(nums: IntArray): IntArray {
        // 1. Calculate the prefix sum
        val prefixSum = IntArray(nums.size)
        prefixSum[0] = nums[0]

        for (i in 1 until nums.size ) {
            prefixSum[i] = prefixSum[i - 1] + nums[i]
        }

        // 2. Store the possible values on the hashmap
        var start = -1
        var end = -1
        val hashMap = HashMap<Int, Int>()
        for (i in prefixSum.indices) {
            if (hashMap.containsKey(prefixSum[i])) {
                val position = hashMap[prefixSum[i]]!!
                if (i - position > end - start) {
                    start = position
                    end = i
                }
            } else {
                hashMap[prefixSum[i]] = i
            }
        }

        return nums.slice(start + 1..end).toIntArray()
    }
}