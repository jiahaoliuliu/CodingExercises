package arrayandlist.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Find the duplicate
 *
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n]
 * inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 *
 * Example 1:
 *      Input: nums = [1,3,4,2,2]
 *      Output: 2
 *
 * Example 2:
 *      Input: nums = [3,1,3,4,2]
 *      Output: 3
 *
 * Example 3:
 *      Input: nums = [3,3,3,3,3]
 *      Output: 3
 *
 * Constraints:
 *      1 <= n <= 10^5
 *      nums.length == n + 1
 *      1 <= nums[i] <= n
 *      All the integers in nums appear only once except for precisely one integer which appears two
 *      or more times.
 *
 * Follow up:
 * How can we prove that at least one duplicate number must exist in nums?
 * Can you solve the problem in linear runtime complexity?
 */
abstract class FindTheDuplicateNumber {

    abstract fun findDuplicate(nums: IntArray): Int

    @Test
    fun test1() {
        // Given
        val list = intArrayOf(4, 2, 1, 3, 1)

        // When
        val result = findDuplicate(list)

        // Then
        assertEquals(1, result)
    }

    @Test
    fun test2() {
        // Given
        val list = intArrayOf(1,3,4,2,2)

        // When
        val result = findDuplicate(list)

        // Then
        assertEquals(2, result)
    }

    @Test
    fun test3() {
        // Given
        val list = intArrayOf(3,1,3,4,2)

        // When
        val result = findDuplicate(list)

        // Then
        assertEquals(3, result)
    }

    @Test
    fun test4() {
        // Given
        val list = intArrayOf(3,3,3,3,3)

        // When
        val result = findDuplicate(list)

        // Then
        assertEquals(3, result)
    }
}

class FindDuplicateImpl : FindTheDuplicateNumber() {

    /**
     * Using a hashset to write down elements repeated
     */
    override fun findDuplicate(nums: IntArray): Int {
        // 1. Init the variables
        val mySet = HashSet<Int>()

        nums.forEach {
            if (mySet.contains(it)) {
                return it
            } else {
                mySet.add(it)
            }
        }

        return -1
    }
}

class FindTheDuplicateNumberAsLinkedList: FindTheDuplicateNumber() {

    /**
     * The idea is using the numbers as pointers to a certain position in the array
     *
     * Because the values has range from 1 to n, there is not pointer that points
     * to outside of the array
     *
     * Now, as linked list, if there are duplicates, that means there ara cycle:
     * - There is at least two values pointing to the same position
     *
     * We can use first fast and slow pointers to find the cycle
     *
     * Then change of the pointers to the first position, going 1 by one, to find the
     * duplicate
     *
     * Complexity:
     * - Time: O(n)
     * - Space: O(1)
     */
    override fun findDuplicate(nums: IntArray): Int {
        // 1. Fast and slow pointers
        var slowPointer = nums[0]
        var fastPointer = nums[nums[0]]

        // 2. Find the middle of the linked list
        while (slowPointer != fastPointer) {
            slowPointer = nums[slowPointer]
            fastPointer = nums[nums[fastPointer]]
        }

        // Iterate through it to find the Nodes which points to the same node
        slowPointer = 0

        while (slowPointer != fastPointer) {
            slowPointer = nums[slowPointer]
            fastPointer = nums[fastPointer]
        }

        // Return result
        return slowPointer
    }
}