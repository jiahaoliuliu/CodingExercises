package arrayandlist.easy

import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Square of a sorted array
 *
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number
 * sorted in non-decreasing order.
 *
 * Example 1:
 *      Input: nums = [-4,-1,0,3,10]
 *      Output: [0,1,9,16,100]
 *      Explanation: After squaring, the array becomes [16,1,0,9,100].
 *      After sorting, it becomes [0,1,9,16,100].
 *
 * Example 2:
 *      Input: nums = [-7,-3,2,3,11]
 *      Output: [4,9,9,49,121]
 *
 * Constraints:
 *      1 <= nums.length <= 10^4
 *      -10^4 <= nums[i] <= 10^4
 * nums is sorted in non-decreasing order.
 * Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution
 * using a different approach?
 */
abstract class SquareOfASortedArray {

    abstract fun sortedSquares(nums: IntArray): IntArray

    @Test
    fun test1() {
        // Given
        val input = intArrayOf(-4,-1,0,3,10)

        // When
        val result = sortedSquares(input)

        // Then
        assertTrue(intArrayOf(0, 1, 9, 16, 100) contentEquals result)
    }

    @Test
    fun test2() {
        // Given
        val input = intArrayOf(-7,-3,2,3,11)

        // When
        val result = sortedSquares(input)

        // Then
        assertTrue(intArrayOf(4, 9, 9, 49, 121) contentEquals result)
    }
}

class SquareOfASortedArrayImpl: SquareOfASortedArray() {

    /**
     * Initial though
     *
     * Per each one of the elements we can create square of it and put it on the array again
     * but since we have negative numbers, it won't be sorted
     *
     * A better solution
     * Have 3 pointers
     * 1. Point to the beginning of nums
     * 2. Point to the end of nums
     * 3. Point to the end of the solution
     *
     * Loop
     *  - compare abs value of pointer 1 with pointer 2
     *  - square it
     *  - add it to the solution
     */
    override fun sortedSquares(nums: IntArray): IntArray {
        if (nums.isEmpty()) {
            return nums
        }

        if (nums.size == 1) {
            return intArrayOf(nums[0] * nums[0])
        }
        val result = ArrayList<Int>()
        var tippingPoint = -1
        for (position in 1 until nums.size) {
            if (nums[position] == 0) {
                tippingPoint = position
                break
            }

            if (nums[position - 1] < 0 && nums[position ] >= 0) {
                tippingPoint = position
                break
            }
        }

        // If the sign of the items in the list has not changed across all the list
        if (tippingPoint == -1) {
            // If all the elements are positive
            return if (nums[0] >= 0) {
                nums.map{ it * it }.toIntArray()
            } else {
                nums.map{ it * it }.reversed().toIntArray()
            }
        }

        var negativePositions = tippingPoint - 1
        var positivePositions = tippingPoint
        while (negativePositions >= 0 && positivePositions < nums.size) {
            val negativeSquare = nums[negativePositions] * nums[negativePositions]
            val positiveSquare = nums[positivePositions] * nums[positivePositions]
            if (negativeSquare > positiveSquare) {
                result.add(positiveSquare)
                positivePositions ++
            } else {
                result.add(negativeSquare)
                negativePositions --
            }
        }

        return if (negativePositions < 0) {
            for (position in positivePositions until nums.size) {
                result.add(nums[position] * nums[position])
            }
            result.toIntArray()
        } else {
            while (negativePositions >= 0) {
                result.add(nums[negativePositions] * nums[negativePositions])
                negativePositions--
            }
            result.toIntArray()
        }
    }
}