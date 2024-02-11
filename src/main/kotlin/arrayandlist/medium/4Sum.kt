package arrayandlist.medium

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 4 sum
 * Given an array nums of n integers, return an array of all the unique quadruplets
 * [nums[a], nums[b], nums[c], nums[d]] such that:
 *      0 <= a, b, c, d < n
 *      a, b, c, and d are distinct.
 *      nums[a] + nums[b] + nums[c] + nums[d] == target
 *  You may return the answer in any order.
 * Example 1:
 *  - Input: nums = [1,0,-1,0,-2,2], target = 0
 *  - Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * Example 2:
 *  - Input: nums = [2,2,2,2,2], target = 8
 *  - Output: [[2,2,2,2]]
 */
class `4Sum` {

    /**
     * Solution based on CodeCryptic
     * https://www.youtube.com/watch?v=vBfqUNPAx30
     */
    private fun solution(nums: IntArray, target: Int): List<List<Int>> {
        val sortedList = nums.sorted().map { it.toLong() }
        val result = mutableListOf<List<Long>>()
        for (a in sortedList.indices) {
            // To avoid to put the same element in the list
            if (a > 0 && sortedList[a] == sortedList[a - 1]) continue
            for (b in a+1 until sortedList.size) {
                // To avoid the duplication of b
                if (b > a + 1 && sortedList[b] == sortedList[b - 1]) continue
                var c = b + 1
                var d = sortedList.size - 1
                while (c < d) {
                    val quad = listOf(sortedList[a], sortedList[b], sortedList[c], sortedList[d])
                    val quadSum:Long = quad.sum()
                    if (quadSum > target) d--
                    else if (quadSum < target) c++
                    else { // The quad sum is the target. We found the result
                        result.add(quad)
                        while (c < d && sortedList[c] == quad[2]) c++
                        while (c < d && sortedList[d] == quad[3]) d--
                    }
                }
            }
        }

        return result.map { longList -> longList.map { long -> long.toInt()}}
    }

    @Test
    fun test1() {
        // Given
        val input = intArrayOf(1, 0, -1, 0, -2, 2)
        val target = 0

        // When
        val result = solution(input, target)

        // Then
        assertEquals(3, result.size)
        assertTrue(result.contains(listOf(-2, -1, 1, 2)))
        assertTrue(result.contains(listOf(-2, 0, 0, 2)))
        assertTrue(result.contains(listOf(-1, 0, 0, 1)))
    }

    @Test
    fun test2() {
        // Given
        val input = intArrayOf(2, 2, 2, 2, 2)
        val target = 8

        // When
        val result = solution(input, target)

        // Then
        assertTrue(listOf(listOf(2, 2, 2, 2)) == result)
    }

    @Test
    fun test3() {
        // Given
        val input = intArrayOf(1_000_000_000,1_000_000_000,1_000_000_000,1_000_000_000)
        val target = -294967296

        // When
        val result = solution(input, target)

        // Then
        assertTrue(emptyList<Int>() == result)
    }
}