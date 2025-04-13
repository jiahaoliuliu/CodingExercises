package arrayandlist.medium

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.math.max
import kotlin.math.min

/**
 * Container with most water
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two
 * endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the
 * most water.
 *
 * Return the maximum amount of water a container can store.
 * Notice that you may not slant the container.
 * Example 1:
 *      Input: height = [1,8,6,2,5,4,8,3,7]
 *      Output: 49
 *      Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case,
 *      the max area of water (blue section) the container can contain is 49.
 *
 * Example 2:
 *      Input: height = [1,1]
 *      Output: 1
 *
 * Constraints:
 *      n == height.length
 *      2 <= n <= 10^5
 *      0 <= height[i] <= 10^4
 */

abstract class ContainerWithMostWater() {

    /**
     * Initial thoughts
     * [1,8,6,2,5,4,8,3,7]
     * So this is a problem that requires both the height of the item as well as the index of the item itself
     * Area of a rectangle = Length x width
     * - length: Index of the item on the right - index of the item on the left
     * - width: min (high of the left item, high of the right item)
     *
     * Known conditions
     * - There is a left pointer
     * - There is a right pointer
     * - The index of the right pointer need to be bigger than the index of the left pointer
     *
     * Given two pointer [1, 8],
     * - the length = 1 - 0 = 1
     * - the width = min (1, 8) = 1
     * The are is 1 x 1 = 1
     *
     * Given an array [1, 8, 6]
     * - There are 3 combinations possibles
     *   - [1, 8, _]
     *      - length = 1 - 0 = 1
     *      - width = min(1, 8) = 1
     *      - area = 1
     *   - [_, 1, 6]
     *      - length = 2 - 0 = 2
     *      - width = min(1, 6) = 1
     *      - area = 2 * 1 = 2
     *   - [_, 8, 6]
     *      - length = 2 - 1 = 1
     *      - width = min(8, 6) = 6
     *      - area = 1 * 6 = 6
     * The maximum area is [8, 6], with area 6
     *
     * The maximum area will be the one with a better proportion of maximum width and maximum length
     *
     * The items in the array are not related between them (they are random),
     * so we need to go through all them
     * Can we sort the array to create an artificial relation? No.
     *
     * As brute force, we can generate all the combinations
     *
     * Could it be divided into subproblems:
     * - Yes. Each subproblem is an array of smaller size
     * but the subproblems are not overlapping because the numbers are random
     *
     * Result of array with length n is
     * -> Max(area of position (0, n - 1), maxArea(array, 1, n - 1), maxArea(array, 0, n - 2)
     * Generalizing
     * -> Max(area of position(i, j), maxArea(array, i+1, j), maxArea(array, i, j-1)
     *
     * Do they overlap?
     * area(array, i, j) = (j - i) * min(i, j)
     *
     * Example:
     * [1,8,6,2], size 4
     *
     * Base case: MaxArea(array, i, j)
     * - if j = i + 1 return ara(array, i, j)
     *
     * maxArea(array, 0, 3) = max(area(array, 0, 3), maxArea(array, 1, 3), maxArea(array, 0, 2))
     * maxArea(array, 1, 3) = max(area(array, 1, 3), maxArea(array, 2, 3), maxArea(array, 1, 2))
     * maxArea(array, 0, 2) = max(area(array, 0, 2), maxArea(array, 1, 2), maxArea(array, 0, 1))
     *
     * maxArea(array, 2, 3) = base case = area(array, 2, 3)
     * maxArea(array, 1, 2) = base case = area(array, 1, 2)
     *
     * maxArea(array, 1, 2) => Already calculated
     * maxArea(array, 0, 1) = base case = area(array, 0, 1)
     *
     * There is overlapping
     *
     * With memoization, we can build a table of (n - 2) * (n - 2)
     *
     * ----------------------
     * two pointer solution
     *
     * Left to the 0
     * Right to the n - 1
     * Init max area = 0
     *
     * condition of stop:
     *  - Right >= right
     * Condition to increase left pointer
     * - Since we want to maximize the area, if left pointer is smaller than the right pointer, increase it
     * Condition to decrease right pointer
     * - Since we want to maximize the area, if right pointer is smaller than the left pointer, decrease it
     *
     * Every time any pointer is moved
     * - Recalculate the area
     * - Update the max area
     */
    abstract fun maxArea(height: IntArray): Int

    @ParameterizedTest(name = "The container with most water of {0} should be {1}")
    @MethodSource("getData")
    fun test(array1: IntArray, expectedValue: Int) {
        val result = maxArea(array1)
        assertEquals(expectedValue, result,)
    }

    companion object {

        @JvmStatic
        fun getData(): List<Array<Any>> {
            return listOf(
                arrayOf(intArrayOf(1,8,6,2,5,4,8,3,7), 49),
                arrayOf(intArrayOf(1, 1), 1),
            )
        }
    }
}

class ContainerWithMostWaterImpl : ContainerWithMostWater() {
    override fun maxArea(height: IntArray): Int {
        var leftWall = 0
        var rightWall = height.lastIndex
        var maxContainerSoFar = 0

        while (leftWall < rightWall) {
            val width = rightWall - leftWall
            val area = width * min(height[leftWall], height[rightWall])
            maxContainerSoFar = max(maxContainerSoFar, area)
            if (height[leftWall] < height[rightWall]) {
                leftWall++
            } else {
                rightWall--
            }
        }

        return maxContainerSoFar
    }
}

class ContainerWithMostWaterOptim : ContainerWithMostWater() {
    override fun maxArea(height: IntArray): Int {
        var max = 0
        var leftPointer = 0
        var rightPointer = height.size - 1
        while(leftPointer < rightPointer) {
            var tmpMax = rightPointer - leftPointer
            if (height[leftPointer] > height[rightPointer]) {
                tmpMax *= height[rightPointer]
                rightPointer--
            } else {
                tmpMax *= height[leftPointer]
                leftPointer++
            }
            if (tmpMax > max) max = tmpMax
        }
        return max

    }
}