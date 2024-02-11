package arrayandlist.easy

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Contains duplicate
 *
 * Given an integer array nums, return true if any value appears at least twice in the array, and return false
 * if every element is distinct.
 * Example 1:
 *      Input: nums = [1,2,3,1]
 *      Output: true
 *
 * Example 2:
 *      Input: nums = [1,2,3,4]
 *      Output: false
 *
 * Example 3:
 *      Input: nums = [1,1,1,3,3,4,3,2,4,2]
 *      Output: true
 *
 * Constraints:
 *  1 <= nums.length <= 10^5
 *  -10^9 <= nums[i] <= 10^9
 */
class ContainsDuplicate {
    /**
     * Initial thought
     * Create a hashMap to store the existing data
     * - If the key exists in the hash map, return false
     * - if after all the content of the nums is checked, return true
     */
    private fun containsDuplicate(nums: IntArray): Boolean {
        // 1. Init the values
        val myDict = HashMap<Int, Boolean>()

        // 2. Iterate
        nums.forEach { item ->
            if (myDict.contains(item)) {
                return true
            } else {
                myDict[item] = true
            }
        }
        // 3. Return the default response
        return false
    }

    /**
     * Optimization. Since the value for the hash map is not very useful, we can use a set instead
     * A set is a list of unique elements
     * The add method on the set will return
     * - true if the item has been added
     * - false if the set already has the item
     */
    private fun containsDuplicate2(nums: IntArray): Boolean {
        // 1. Init the values
        val mySet = mutableSetOf<Int>()

        // 2. Iterate
        nums.forEach { item ->
            if (!mySet.add(item)) {
                return true
            }
        }
        // 3. Return the default response
        return false
    }

    @Test
    fun test1() {
        // Given
        val input = intArrayOf(1, 2, 3, 1)

        // When
        val result = containsDuplicate(input)

        // Then
        assertTrue(result)
    }

    @Test
    fun test2() {
        // Given
        val input = intArrayOf(1, 2, 3, 4)

        // When
        val result = containsDuplicate(input)

        // Then
        assertFalse(result)
    }

    @Test
    fun test3() {
        // Given
        val input = intArrayOf(1,1,1,3,3,4,3,2,4,2)

        // When
        val result = containsDuplicate(input)

        // Then
        assertTrue(result)
    }

    @Test
    fun test4() {
        // Given
        val input = intArrayOf(1, 2, 3, 1)

        // When
        val result = containsDuplicate2(input)

        // Then
        assertTrue(result)
    }

    @Test
    fun test5() {
        // Given
        val input = intArrayOf(1, 2, 3, 4)

        // When
        val result = containsDuplicate2(input)

        // Then
        assertFalse(result)
    }

    @Test
    fun test6() {
        // Given
        val input = intArrayOf(1,1,1,3,3,4,3,2,4,2)

        // When
        val result = containsDuplicate2(input)

        // Then
        assertTrue(result)
    }

}