package arrayandlist

import junit.framework.Assert.*
import org.junit.Test

/**
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
 * The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
 * Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the
 * following things:
 *    - Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
 *    - Return k.
 * Example 1
 * Input: nums = [3,2,2,3], val = 3
 * Output: 2, nums = [2,2,_,_]
 *
 * Input: nums = [0,1,2,2,3,0,4,2], val = 2
 * Output: 5, nums = [0,1,4,0,3,_,_,_]
 */
class RemoveElement {
    /**
     * Initial though
     *  - Corner case
     *    - If the list is empty
     *        - Return 0
     *  - We need 2 pointes
     *    - 1 to point next space to be filled
     *    - 1 to point the next element to be checked
     * - Since all the elements needs to be moved to the beginnig of the array
     * and the order doesn't matter
     *   - One pointer points to the beginning of the array
     *      - looking for the position to be filled
     *   - One pointer points to the beginning of the array
     * algorithm
     *   - Just move the valid item from the back to the front
     */
    fun removeElement(nums: IntArray, `val`: Int): Int {
        var i = 0
        nums.forEachIndexed {index, item ->
            if (item != `val`) {
                nums[i] = item
                i++
            }
        }
        return i
    }

    @Test
    fun test1() {
        // Given
        val myArray = intArrayOf(3, 2, 2, 3)
        val value = 3

        // When
        val result = removeElement(myArray, value)

        // Then
        assertEquals(2, myArray[0])
        assertEquals(2, myArray[1])
        assertEquals(result, 2)
    }

    @Test
    fun test2() {
        // Given
        val myArray = intArrayOf(0,1,2,2,3,0,4,2)
        val value = 2

        // When
        val result = removeElement(myArray, value)

        // Then
        assertEquals(5, result)
        val subList = myArray.toMutableList().subList(0, 5)
        assertTrue(subList.remove(0))
        assertTrue(subList.remove(1))
        assertTrue(subList.remove(4))
        assertTrue(subList.remove(0))
        assertTrue(subList.remove(3))
    }

    @Test
    fun test3() {
        // Given
        val myArray = intArrayOf(1)
        val value = 1

        // When
        val result = removeElement(myArray, value)

        // Then
        assertEquals(result, 0)
//        assertTrue(myArray.isEmpty())
    }

    @Test
    fun test4() {
        // Given
        val myArray = intArrayOf(3, 3)
        val value = 3

        // When
        val result = removeElement(myArray, value)

        // Then
        assertEquals(result, 0)
        assertTrue(intArrayOf(3, 3) contentEquals myArray)
    }

    @Test
    fun test5() {
        // Given
        val myArray = intArrayOf(2)
        val value = 3

        // When
        val result = removeElement(myArray, value)

        // Then
        assertEquals(1, result)
        assertTrue(intArrayOf(2) contentEquals myArray)
    }
}