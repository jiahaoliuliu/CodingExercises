package arrayandlist.medium

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Find all duplicates in an array
 *
 * Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each
 * integer appears once or twice, return an array of all the integers that appears twice.
 *
 * You must write an algorithm that runs in O(n) time and uses only constant extra space.
 *
 * Example 1:
 *      Input: nums = [4,3,2,7,8,2,3,1]
 *      Output: [2,3]
 *
 * Example 2:
 *      Input: nums = [1,1,2]
 *      Output: [1]
 *
 * Example 3:
 *      Input: nums = [1]
 *      Output: []
 *
 * Constraints:
 *      n == nums.length
 *      1 <= n <= 10^5
 *      1 <= nums[i] <= n
 *      Each element in nums appears once or twice.
 */
class FindAllDuplicatesInAnArray {

    /**
     * Initial thought
     *
     * Since all the elements might appear once or twice (not more)
     * It is enough to keep a hashset of seen items
     *
     * for each item in the list
     * -> If it is already on the seen item, add it to the result
     * -> Otherwise add it to the set
     *
     * return the result
     *
     */
    private fun findDuplicates(nums: IntArray): List<Int> {
        // 1. init the variables
        val result = mutableListOf<Int>()
        val seen = hashSetOf<Int>()

        // 2. Loop
        for (num in nums) {
            if (seen.contains(num)) {
                result.add(num)
            } else {
                seen.add(num)
            }
        }

        // 3. return the result
        return result
    }

    @Test
    fun test1() {
        // Given
        val nums = intArrayOf(4,3,2,7,8,2,3,1)

        // When
        val result = findDuplicates(nums)

        // Then
        assertEquals(listOf(2, 3), result)
    }

    @Test
    fun test2() {
        // Given
        val nums = intArrayOf(1, 1, 2)

        // When
        val result = findDuplicates(nums)

        // Then
        assertEquals(listOf(1), result)
    }

    @Test
    fun test3() {
        // Given
        val nums = intArrayOf(1)

        // When
        val result = findDuplicates(nums)

        // Then
        assertEquals(listOf<Int>(), result)
    }

}