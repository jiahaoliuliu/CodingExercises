package arrayandlist.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Majority element
 *
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that
 * the majority element always exists in the array.
 *
 * Example 1:
 *      Input: nums = [3,2,3]
 *      Output: 3
 *
 * Example 2:
 *      Input: nums = [2,2,1,1,1,2,2]
 *      Output: 2
 *
 * Constraints:
 *      n == nums.length
 *      1 <= n <= 5 * 10^4
 *      -10^9 <= nums[i] <= 10^9
 */
class MajorityElement {

    /**
     * Initial thoughts
     *
     * Create frequency map
     * If at any moment the number exceeds the total number /2
     * return it
     *
     * if not, return -1
     *
     * Complexity:
     * - Time: O(n)
     * - Space: O(n)
     */
    private fun majorityElement(nums: IntArray): Int {
        // 1. Init variables
        val halfFrequent = nums.size / 2
        val frequencyMap = HashMap<Int, Int>()

        // 2. Loop
        nums.forEach {
            var frequency = frequencyMap.getOrDefault(it, 0)
            frequency++
            if (frequency > halfFrequent) {
                return it
            }

            frequencyMap[it] = frequency
        }

        //3. Return default value
        return 0
    }

    @Test
    fun test1() {
        // Given
        val nums = intArrayOf(3, 2, 3)

        // When
        val result = majorityElement(nums)

        // Then
        assertEquals(3, result)
    }

    @Test
    fun tes2() {
        // Given
        val nums = intArrayOf(2,2,1,1,1,2,2)

        // When
        val result = majorityElement(nums)

        // Then
        assertEquals(2, result)
    }

    /**
     * Boyer-Moore Algorithm to find the majority of the element
     *
     * https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
     *
     * If theres is a majority of items and its occurrence is more than half
     * then we can use it to "cancel" the rest of the element in the array
     *
     * Complexity:
     *  Time: O(n)
     *  Space: O(1)
     *
     */
    private fun majorityElementBoyerMooreAlgorithm(nums: IntArray): Int {
        // 1. Init variables
        var candidate = 0
        var count = 0

        // 2. Loop
        nums.forEach {
            if (count == 0) candidate = it
            if (it == candidate) count++
            else count--
        }

        //3. Return candidate
        return candidate
    }

    @Test
    fun testBoyerMooreAlgorithm1() {
        // Given
        val nums = intArrayOf(3, 2, 3)

        // When
        val result = majorityElementBoyerMooreAlgorithm(nums)

        // Then
        assertEquals(3, result)
    }

    @Test
    fun testBoyerMooreAlgorithm2() {
        // Given
        val nums = intArrayOf(2,2,1,1,1,2,2)

        // When
        val result = majorityElementBoyerMooreAlgorithm(nums)

        // Then
        assertEquals(2, result)
    }
}