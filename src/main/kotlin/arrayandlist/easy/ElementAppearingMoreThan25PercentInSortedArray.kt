package arrayandlist.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Element Appearing More Than 25% In Sorted Array
 *
 * Given an integer array sorted in non-decreasing order, there is exactly one integer in the array
 * that occurs more than 25% of the time, return that integer.
 *
 * Example 1:
 *      Input: arr = [1,2,2,6,6,6,6,7,10]
 *      Output: 6
 *
 * Example 2:
 *      Input: arr = [1,1]
 *      Output: 1
 *
 * Constraints:
 *      1 <= arr.length <= 10^4
 *      0 <= arr[i] <= 10^5
 *
 */
abstract class ElementAppearingMoreThan25PercentInSortedArray {

    abstract fun findSpecialInteger(arr: IntArray): Int

    @Test
    fun test1() {
        // Given
        val arr = intArrayOf(1,2,2,6,6,6,6,7,10)

        // When
        val result = findSpecialInteger(arr)

        // Then
        assertEquals(6, result)
    }

    @Test
    fun test2() {
        // Given
        val arr = intArrayOf(1,1)

        // When
        val result = findSpecialInteger(arr)

        // Then
        assertEquals(1, result)
    }

    @Test
    fun test3() {
        // Given
        val arr = intArrayOf(1,2,3,4,5,6,7,8,9,10,11,12,12,12,12)

        // When
        val result = findSpecialInteger(arr)

        // Then
        assertEquals(12, result)
    }
}

class ElementAppearingMoreThan25PercentInSortedArrayFrequencyMap: ElementAppearingMoreThan25PercentInSortedArray() {

    /**
     * Using the frequency map to find the element
     * Complexity:
     * - Time: O(n)
     * - Space: O(n)
     */
    override fun findSpecialInteger(arr: IntArray): Int {
        val frequencyMap = HashMap<Int, Int>()

        arr.forEach {
            var frequency = frequencyMap.getOrDefault(it, 0)
            frequency++
            if (frequency > arr.size / 4) {
                return it
            }
            frequencyMap[it] = frequency
        }

        return -1
    }
}

class ElementAppearingMoreThan25PercentInSortedArraySlidingWindows: ElementAppearingMoreThan25PercentInSortedArray() {

    /**
     * Using sliding windows of fix size n/4 to check
     * Because the elements are sorted, then if the first element of the windows is the
     * same as the last element, then that's the element which has at least n/4 repeated
     *
     * Complexity:
     * - Time: O(n)
     * - Space: O(1)
     */
    override fun findSpecialInteger(arr: IntArray): Int {
        for (i in 0 until arr.size - arr.size/4) {
            if (arr[i] == arr[i + arr.size/4]) {
                return arr[i]
            }
        }

        return -1
    }
}

class ElementAppearingMoreThan25PercentInSortedArrayBinarySearch: ElementAppearingMoreThan25PercentInSortedArray() {

    /**
     * The idea is the following:
     * For any element that appears more than 25% in the array, it must be in one of the
     * points [0, n/4, n/2, 3n/4, n-1]
     *
     * First we check if any of those points are the same
     * - If so, that's the element to find
     *
     * Secondly, if we cannot find it, we check the leftest element and rightest element
     * If the difference between them is bigger than n/4, then that's our element
     *
     *
     */
    override fun findSpecialInteger(arr: IntArray): Int {
        val n = arr.size
        if (n < 4) {
            return arr[0]
        }

        val candidates = intArrayOf(arr[0], arr[n/4], arr[n/2], arr[3*n/4], arr[n - 1])
        for (i in 0 until candidates.size - 1) {
            if (candidates[i] == candidates[i+1]) {
                return candidates[i]
            }
        }

        for (i in 1 .. 3) {
            val leftest = findLeftest(arr, candidates[i])
            val rightest = findRightest(arr, candidates[i])
            if (rightest - leftest + 1 > arr.size / 4) {
                return candidates[i]
            }
        }

        return -1
    }

    /**
     * (1,2,2,6,6,6,6,7,10). Size = 9, target = 6
     *
     */
    private fun findLeftest(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size - 1
        while (left <= right) {
            val middle = left + (right - left) / 2
            if (arr[middle] == target) {
                if (middle == 0 || arr[middle - 1] < target) {
                    return middle
                } else {
                    right = middle - 1
                }
            } else if (arr[middle] < target) {
                left = middle + 1
            } else {
                right = middle - 1
            }
        }

        return -1
    }

    private fun findRightest(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size - 1
        while (left <= right) {
            val middle = left + (right - left) / 2
            if (arr[middle] == target) {
                if (middle == arr.size - 1|| arr[middle + 1] > target) {
                    return middle
                } else {
                    left = middle + 1
                }
            } else if (arr[middle] < target) {
                left = middle + 1
            } else {
                right = middle - 1
            }
        }

        return -1
    }
}